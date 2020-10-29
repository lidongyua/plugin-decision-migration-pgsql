package com.fr.plugin.migration.pgsql;

import com.fr.third.org.hibernate.dialect.PostgresPlusDialect;

import java.sql.Types;

/**
 * @author lidongy
 * @version 10.0
 * Created by lidongy on 2020/7/30
 */
public class FRPGSQLDialect extends PostgresPlusDialect {
    public FRPGSQLDialect() {
        super();
        registerColumnType( Types.VARCHAR, 65536, "varchar($l)" );
        registerColumnType( Types.VARCHAR, "text" );
    }
}
