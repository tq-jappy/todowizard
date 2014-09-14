package todowizard.core.doma;

import org.seasar.doma.jdbc.dialect.Db2Dialect;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.H2Dialect;
import org.seasar.doma.jdbc.dialect.HsqldbDialect;
import org.seasar.doma.jdbc.dialect.MssqlDialect;
import org.seasar.doma.jdbc.dialect.MysqlDialect;
import org.seasar.doma.jdbc.dialect.OracleDialect;
import org.seasar.doma.jdbc.dialect.PostgresDialect;
import org.seasar.doma.jdbc.dialect.SqliteDialect;

/**
 * @author t_endo
 *
 */
public class DialectUtil {

    /**
     * 
     * @param driverClass
     * @return
     */
    public static Dialect inferDialect(String driverClass) {
        if (driverClass == null) {
            return null;
        }
        switch (driverClass) {
        case "org.postgresql.Driver":
            return new PostgresDialect();
        case "org.h2.Driver":
            return new H2Dialect();
        case "com.mysql.jdbc.Driver":
            return new MysqlDialect();
        case "oracle.jdbc.driver.OracleDriver":
            return new OracleDialect();
        case "com.microsoft.sqlserver.jdbc.SQLServerDriver":
            return new MssqlDialect();
        case "com.ibm.db2.jcc.DB2Driver":
            return new Db2Dialect();
        case "org.hsqldb.jdbcDriver":
            return new HsqldbDialect();
        case "org.sqlite.JDBC":
            return new SqliteDialect();
        default:
            return null;
        }
    }
}
