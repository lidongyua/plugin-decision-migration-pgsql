package com.fr.plugin.migration.pgsql;

import com.fr.decision.fun.impl.AbstractControllerRegisterProvider;
import com.fr.intelli.record.Focus;
import com.fr.intelli.record.Original;
import com.fr.record.analyzer.EnableMetrics;

/**
 * @author lidongy
 * @version 10.0
 * Created by lidongy on 2020/10/29
 */
@EnableMetrics
public class PGSQLControllerBridge extends AbstractControllerRegisterProvider {
    @Override
    @Focus(id = "com.fr.plugin.migration.pgsql", text = "", source = Original.PLUGIN)
    public Class<?>[] getControllers() {
        return new Class[]{PGSQLController.class};
    }
}
