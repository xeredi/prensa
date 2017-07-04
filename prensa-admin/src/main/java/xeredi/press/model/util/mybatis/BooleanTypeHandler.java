package xeredi.press.model.util.mybatis;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class BooleanTypeHandler.
 */
public final class BooleanTypeHandler implements TypeHandler<Boolean> {

    /** The Constant TRUE_INT. */
    private static final int TRUE_INT = 1;

    /** The Constant FALSE_INT. */
    private static final int FALSE_INT = 0;

    /**
     * Gets the result.
     *
     * @param resultSet
     *            the result set
     * @param columnName
     *            the column name
     * @return the result
     * @throws SQLException
     *             the sQL exception
     * @see TypeHandler#getResult(ResultSet, String)
     */
    @Override
    public Boolean getResult(final ResultSet resultSet, final String columnName) throws SQLException {
        final int value = resultSet.getInt(columnName);
        Boolean valueBoolean = null;

        if (!resultSet.wasNull()) {
            if (value == FALSE_INT) {
                valueBoolean = Boolean.FALSE;
            } else {
                valueBoolean = Boolean.TRUE;
            }
        }

        return valueBoolean;
    }

    /**
     * Gets the result.
     *
     * @param resultSet the result set
     * @param columnIndex the column index
     * @return the result
     * @throws SQLException the sQL exception
     * @see org.apache.ibatis.type.TypeHandler#getResult(java.sql.ResultSet, int)
     */
    @Override
    public Boolean getResult(final ResultSet resultSet, final int columnIndex) throws SQLException {
        final int value = resultSet.getInt(columnIndex);
        Boolean valueBoolean = null;

        if (!resultSet.wasNull()) {
            if (value == FALSE_INT) {
                valueBoolean = Boolean.FALSE;
            } else {
                valueBoolean = Boolean.TRUE;
            }
        }

        return valueBoolean;
    }

    /**
     * Gets the result.
     *
     * @param statement
     *            the statement
     * @param columnIndex
     *            the column index
     * @return the result
     * @throws SQLException
     *             the sQL exception
     * @see TypeHandler#getResult(CallableStatement, int)
     */
    @Override
    public Boolean getResult(final CallableStatement statement, final int columnIndex) throws SQLException {
        final int value = statement.getInt(columnIndex);
        Boolean valueBoolean = null;

        if (!statement.wasNull()) {
            if (value == FALSE_INT) {
                valueBoolean = Boolean.FALSE;
            } else {
                valueBoolean = Boolean.TRUE;
            }
        }

        return valueBoolean;
    }

    /**
     * Sets the parameter.
     *
     * @param statement
     *            the statement
     * @param index
     *            the index
     * @param parameter
     *            the parameter
     * @param type
     *            the type
     * @throws SQLException
     *             the sQL exception
     * @see TypeHandler#setParameter(PreparedStatement, int, Object, JdbcType)
     */
    @Override
    public void setParameter(final PreparedStatement statement, final int index, final Boolean parameter,
            final JdbcType type) throws SQLException {
        if (parameter == null) {
            statement.setNull(index, Types.INTEGER);
        } else {
            int value = FALSE_INT;

            if (Boolean.TRUE.equals(parameter)) {
                value = TRUE_INT;
            }

            statement.setInt(index, value);
        }
    }
}
