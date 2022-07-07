package ru.otus.springwork14.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import ru.otus.springwork14.model.Author;
import ru.otus.springwork14.model.AuthorDto;
import ru.otus.springwork14.repository.jpa.AuthorJpaRepository;
import ru.otus.springwork14.repository.mongo.AuthorMongoRepository;
import ru.otus.springwork14.service.AuthorService;

import java.util.Collections;
import java.util.List;

import static ru.otus.springwork14.common.Constant.*;

@Slf4j
@EnableBatchProcessing
@Component
@RequiredArgsConstructor
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private final AuthorJpaRepository repository;

    @Autowired
    private final AuthorMongoRepository repositoryMongo;

    @Autowired
    private final AuthorService authorService;

    @StepScope
    @Bean
    public RepositoryItemReader<Author> reader() {
        return new RepositoryItemReaderBuilder<Author>()
                .repository(repository)
                .sorts(Collections.singletonMap("id", Sort.Direction.ASC))
                .methodName("findAll")
                .pageSize(1000)
                .saveState(true)
                .name(ITEM_READER)
                .build();
    }

    @StepScope
    @Bean
    public ItemProcessor<Author, AuthorDto> processor(AuthorService authorService) {
        return authorService::toDto;
    }

    @StepScope
    @Bean
    public RepositoryItemWriter<AuthorDto> writer() {
        return new RepositoryItemWriterBuilder<AuthorDto>()
                .repository(repositoryMongo)
                .methodName("save")
                .build();
    }

    @Bean
    public Step migrateStep(ItemReader<Author> reader,
                      ItemProcessor<Author, AuthorDto> processor,
                      ItemWriter<AuthorDto> writer) {
        return stepBuilderFactory.get(MIGRATE_STEP)
                .<Author, AuthorDto>chunk(COUNT_CHUNK)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .listener(new ItemReadListener<>() {
                    public void beforeRead() {
                        log.info("Начало чтения");
                    }

                    public void afterRead(@NonNull Author o) {
                        log.info("Конец чтения");
                    }

                    public void onReadError(@NonNull Exception e) {
                        log.info("Ошибка чтения");
                    }
                })
                .listener(new ItemWriteListener<>() {
                    public void beforeWrite(@NonNull List list) {
                        log.info("Начало записи");
                    }

                    public void afterWrite(@NonNull List list) {
                        log.info("Конец записи");
                    }

                    public void onWriteError(@NonNull Exception e, @NonNull List list) {
                        log.info("Ошибка записи");
                    }
                })
                .listener(new ItemProcessListener<>() {
                    public void beforeProcess(Author o) {
                        log.info("Начало обработки");
                    }

                    public void afterProcess(@NonNull Author o, AuthorDto o2) {
                        log.info("Конец обработки");
                    }

                    public void onProcessError(@NonNull Author o, @NonNull Exception e) {
                        log.info("Ошибка обработки");
                    }
                })
                .listener(new ChunkListener() {
                    public void beforeChunk(@NonNull ChunkContext chunkContext) {
                        log.info("Начало пачки");
                    }

                    public void afterChunk(@NonNull ChunkContext chunkContext) {
                        log.info("Конец пачки");
                    }

                    public void afterChunkError(@NonNull ChunkContext chunkContext) {
                        log.info("Ошибка пачки");
                    }
                })
                .build();
    }

    @Bean
    public Job importFactoryJob(Step step) {
        return jobBuilderFactory.get(IMPORT_JOB)
                .incrementer(new RunIdIncrementer())
                .start(step)
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(@NonNull JobExecution jobExecution) {
                        log.info("Начало job");
                    }

                    @Override
                    public void afterJob(@NonNull JobExecution jobExecution) {
                        log.info("Конец job");
                    }
                })
                .build();
    }
}
