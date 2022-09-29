package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.config.Config;
import edu.javacourse.studentorder.domain.CountryArea;
import edu.javacourse.studentorder.domain.PassportOffice;
import edu.javacourse.studentorder.domain.Street;
import edu.javacourse.studentorder.domain.RegisterOffice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DctionaryDaoImpl implements DictionaryDao {
    private static final Logger logger= LoggerFactory.getLogger(DctionaryDaoImpl.class);
    private static final String GET_STREET = "SELECT street_code,street_name FROM jc_street WHERE UPPER(street_name) LIKE UPPER(?)";
    private static final String GET_PASSPORT = "SELECT * " + "FROM jc_passport_office WHERE p_office_area_id=?";
    private static final String GET_REGISTER = "SELECT * " + "FROM jc_register_office WHERE r_office_area_id=?";
    private static final String GET_AREA = "SELECT * FROM jc_country_struct WHERE area_id LIKE ? and area_id<>?";


    private Connection getConnection() throws SQLException {
        return ConnectionBuilder.getConnection();
    }

    @Override
    public List<Street> findStreet(String pattern) throws DaoException {
        List<Street> streets = new LinkedList<>();
        try (Connection connection = getConnection();
             PreparedStatement stat = connection.prepareStatement(GET_STREET)) {
            stat.setString(1, "%" + pattern + "%");
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                Street streeter = new Street(rs.getLong("street_code"), rs.getString("street_name"));
                streets.add(streeter);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            throw new DaoException();
        }

        return streets;
    }

    @Override
    public List<PassportOffice> findPassportOffices(String areaId) throws DaoException {
        List<PassportOffice> passportOffices = new LinkedList<>();
        try (Connection connection = getConnection();
             PreparedStatement stat = connection.prepareStatement(GET_PASSPORT)) {
            stat.setString(1, areaId);
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                PassportOffice pasport = new PassportOffice(rs.getLong("p_office_area_id"), rs.getString("p_office_id"), rs.getString("p_office_name"));
                passportOffices.add(pasport);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            throw new DaoException();
        }

        return passportOffices;
    }


    @Override
    public List<RegisterOffice> findRegisterOffices(String registerId) throws DaoException {
        List<RegisterOffice> registerOffices = new LinkedList<>();
        try (Connection connection = getConnection();
             PreparedStatement stat = connection.prepareStatement(GET_REGISTER)) {
            stat.setString(1, registerId);
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                RegisterOffice register = new RegisterOffice(rs.getLong("r_office_area_id"), rs.getString("r_office_id"), rs.getString("r_office_name"));
                registerOffices.add(register);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            throw new DaoException();
        }

        return registerOffices;
    }

    @Override
    public List<CountryArea> findAreas(String areaId) throws DaoException {
        List<CountryArea> areas = new LinkedList<>();
        try (Connection connection = getConnection();
             PreparedStatement stat = connection.prepareStatement(GET_AREA)) {
            String param1 = buildParam(areaId);
            String param2 = areaId;
            stat.setString(1, param1);
            stat.setString(2, param2);
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                CountryArea countryArea = new CountryArea(rs.getString("area_id"), rs.getString("area_name"));
                areas.add(countryArea);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            throw new DaoException();
        }

        return areas;
    }

    public static String buildParam(String areaId) throws SQLException {
        if (areaId == null || areaId.trim().isEmpty()) {
            return "__0000000000";
        } else if (areaId.endsWith("0000000000")) {
            return areaId.substring(0, 2) + "___0000000";
        } else if (areaId.endsWith("0000000")) {
            return areaId.substring(0, 5) + "___0000";
        } else if (areaId.endsWith("0000")) {
            return areaId.substring(0, 8) + "____";
        }
        throw new SQLException("Invalid parametr 'areaId':" + areaId);

    }
}


