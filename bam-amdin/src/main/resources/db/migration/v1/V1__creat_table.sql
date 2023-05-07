create table if not exists fff{
    uu varchar(255) not null
} Invocation of init method failed; nested exception is org.flywaydb.core.api.FlywayException: Found non-empty schema(s) `bam` but no schema history table. Use baseline() or set baselineOnMigrate to true to initialize the schema history table.