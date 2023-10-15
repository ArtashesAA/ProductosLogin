package com.servicio.bbdd;

import javax.sql.DataSource;

public interface DataSourceConfig {
    DataSource configureDataSource();
}
