package com.fr.plugin.migration.pgsql;

import com.fr.decision.migration.configuration.MigrationDBConfiguration;
import com.fr.decision.webservice.Response;
import com.fr.decision.webservice.annotation.LoginStatusChecker;
import com.fr.decision.webservice.v10.login.LoginService;
import com.fr.decision.webservice.v10.login.TokenResource;
import com.fr.decision.webservice.v10.migration.MigrationService;
import com.fr.record.analyzer.EnableMetrics;
import com.fr.stable.db.option.DBOption;
import com.fr.third.springframework.stereotype.Controller;
import com.fr.third.springframework.web.bind.annotation.RequestMapping;
import com.fr.third.springframework.web.bind.annotation.RequestMethod;
import com.fr.third.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lidongy
 * @version 10.0
 * Created by lidongy on 2020/10/29
 */
@EnableMetrics
@Controller
@LoginStatusChecker(tokenResource = TokenResource.COOKIE)
@RequestMapping("/plugin/pgsql")
public class PGSQLController {

    @RequestMapping(value = "/migration", method = RequestMethod.GET)
    @ResponseBody
    public Response migration(HttpServletRequest req,
                              HttpServletResponse res) throws Exception {
        //设置配置
        DBOption dbOption = new DBOption();
        dbOption.setUrl("jdbc:postgresql://192.168.5.48:5432/demo123");
        dbOption.setUsername("postgres");
        dbOption.setPassword("123");
        //我们工程原始的方言一般存在 third 包中的 com.fr.third.org.hibernate.dialect 下。若没有，则需要去网上找下或自己手撸
        dbOption.setDialectClass("com.fr.plugin.migration.pgsql.FRPGSQLDialect");
        dbOption.setDriverClass("org.postgresql.Driver");

        //模式，若有的话填写
        dbOption.addRawProperty("default_schema", "public");

        //存配置
        MigrationDBConfiguration.getInstance().updateCache(dbOption);

        //获取用户名
        String userName = LoginService.getInstance().getUserNameFromRequestCookie(req);

        //开始迁移。这时候就会用上前面存下来的dbOption
        MigrationService.getInstance().startTransfer(userName);

        return Response.success();
    }
}
