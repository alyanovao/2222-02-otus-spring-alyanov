package ru.otus.springwork14.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@ShellComponent
public class ShellCommand {
    private final Job importJob;
    private final JobLauncher launcher;

    @ShellMethod(value = "startMigration", key = {"start", "s"})
    public void startMigrationJob() throws Exception {
        launcher.run(importJob, new JobParameters());
    }
}
