package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.config.Config;
import edu.javacourse.studentorder.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentDaoImpl implements StudentOrderDao {
    private int limit=Integer.parseInt(Config.getProperty(Config.DB_LIMIT));
    private static final Logger logger= LoggerFactory.getLogger(StudentDaoImpl.class);
    private static final String INSERT_ORDER = "INSERT INTO public.jc_student_order(" +
            "student_order_status,  student_order_date, h_sur_name, h_given_name, h_patronymic, h_date_of_birth, h_passport_seria, h_pasport_number, h_passport_date, h_passport_ofice_id, h_post_index, h_street_code, h_bulding, h_extension, h_apartament, h_university_id, h_student_number, w_sur_name, w_given_name, w_patronymic, w_date_of_birth, w_passport_seria, w_pasport_number, w_passport_date, w_passport_ofice_id, w_post_index, w_street_code, w_bulding, w_extension, w_apartament, w_university_id, w_student_number, certificate_id, register_oficce_id, marriage_date)\n" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";


    private static final String INSERT_CHILD="INSERT INTO public.jc_student_children(" +
            " student_order_id,c_sur_name, c_given_name, c_patronymic, c_date_of_birth, " +
            "c_certificate_number, c_certificate_date, c_register_office_id, c_post_index, " +
            "c_street_code, c_bulding, c_extension, c_apartament)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";


    private static final String SELECT_ORDERS=
            "SELECT so.*,ro.r_office_area_id,ro.r_office_name ," +
            "po_h.p_office_area_id as h_p_office_area_id," +
            "po_h.p_office_name as h_p_office_name," +
            "po_w.p_office_area_id as w_p_office_area_id," +
            "po_w.p_office_name as w_p_office_name " +
            "FROM jc_student_order so " +
            "INNER JOIN jc_register_office ro ON ro.r_office_id = so.register_oficce_id " +
            "INNER JOIN jc_passport_office po_h ON po_h.p_office_id=so.h_passport_ofice_id " +
            "INNER JOIN jc_passport_office po_w ON po_w.p_office_id=so.w_passport_ofice_id " +
            "WHERE student_order_status=? ORDER BY student_order_date LIMIT ?" ;


   private static final String SELECT_CHILD = "SELECT soc.*,ro.r_office_area_id,ro.r_office_name  " +
           " FROM jc_student_children soc " +
           " INNER JOIN jc_register_office ro ON ro.r_office_id=soc.c_register_office_id " +
           " WHERE student_order_id IN ";

    private static final String SELECT_ORDERS_FULL=
            "SELECT so.*,ro.r_office_area_id,ro.r_office_name ," +
                    "po_h.p_office_area_id as h_p_office_area_id," +
                    "po_h.p_office_name as h_p_office_name," +
                    "po_w.p_office_area_id as w_p_office_area_id," +
                    "po_w.p_office_name as w_p_office_name,soc.*, ro_c.r_office_area_id,ro_c.r_office_name "+
                    "FROM jc_student_order so " +
                    "INNER JOIN jc_register_office ro ON ro.r_office_id = so.register_oficce_id " +
                    "INNER JOIN jc_passport_office po_h ON po_h.p_office_id=so.h_passport_ofice_id " +
                    "INNER JOIN jc_passport_office po_w ON po_w.p_office_id=so.w_passport_ofice_id " +
                    "INNER JOIN jc_student_children soc ON soc.student_order_id = so.student_order_id "+
                    "INNER JOIN jc_register_office ro_c ON ro_c.r_office_id=soc.c_register_office_id "+
                    "WHERE student_order_status = ? ORDER BY so.student_order_id LIMIT ? ";

    private Connection getConnection() throws SQLException {
        //Config.getProperty(Config.DB_LIMIT)

        return ConnectionBuilder.getConnection();
    }
    @Override
    public Long saveStudentOrder(StudentOrder so) throws DaoException {
        Long result=-1L;
        logger.debug("SO:{}",so);
        try (Connection connection = getConnection();
             PreparedStatement stat = connection.prepareStatement(INSERT_ORDER,new String[]{"student_order_id"})) {
             //Header
             connection.setAutoCommit(false); //Обобщение всех действий
             try {
                stat.setInt(1, StudentOrderStatus.START.ordinal());

                stat.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));

                //Marriage
                setParamsForAdult(stat, 3, so.getHusband());

                setParamsForAdult(stat, 18, so.getWife());

                //Obshak
                stat.setLong(33, so.getMarriageSertificated());
                stat.setLong(34, so.getMarriageOffice().getOfficeId());
                stat.setDate(35, Date.valueOf(so.getMarriageDate()));
                stat.executeUpdate();
                ResultSet gkrs = stat.getGeneratedKeys();
                if (gkrs.next()) {
                    result = gkrs.getLong(1);
                }
                gkrs.close();
                saveChildren(connection, so, result);
                connection.commit();
            }catch (SQLException e){
                connection.rollback();
                throw e;
            }


        } catch (SQLException e){
            logger.error(e.getMessage(),e);
            throw new DaoException();
        }
        return result;
    }



    private void saveChildren(Connection con,StudentOrder so,Long result) throws SQLException {
        try (PreparedStatement stat = con.prepareStatement(INSERT_CHILD)) {
            //int counter=0;
            for (Child child : so.getList()) {
                stat.setLong(1, result);
                setParamsForChild(stat, child);
                stat.executeUpdate();
//                stat.addBatch();
//                counter++;
//                if(counter>10000) {
//                   stat.executeBatch();
//                    counter = 0;
//                }
//            }
//            if(counter>0){
//                stat.executeBatch();
//            }
//                stat.executeBatch();
            }
        }
    }

    private void setParamsForAdult( PreparedStatement stat, int start, Adult adult) throws SQLException {
        setParamsForPerson(stat,start,adult);
        stat.setString(start +4, adult.getPassportSeria());
        stat.setString(start +5, adult.getPassportNumber());
        stat.setDate(start +6, Date.valueOf(adult.getIssueDate()));
        stat.setLong(start +7, adult.getIssueDepartment().getOfficeId());
        setParamsForAddres(adult,stat, start+8);
        stat.setLong(start+13,adult.getUniversity().getUniversityId());
        stat.setString(start+14,adult.getStudentId());
    }

    private void setParamsForChild(PreparedStatement st,Child child)throws SQLException{
        setParamsForPerson(st,2,child);
        st.setString(6, child.getCertificateNumber());
        st.setDate(7, Date.valueOf(child.getIssueDate()));
        st.setLong(8,child.getIssueDepartment().getOfficeId());
        setParamsForAddres(child,st,9);
    }

    private  void setParamsForPerson(PreparedStatement stat, int start, Person person) throws SQLException {
        stat.setString(start, person.getSurName());
        stat.setString(start +1, person.getGiveName());
        stat.setString(start +2, person.getPatronymic());
        stat.setDate(start +3, Date.valueOf(person.getDateOfBith()));
    }

    private void setParamsForAddres(Person person, PreparedStatement stat, int start) throws SQLException {
        Adress h_adress= person.getAdress();
        stat.setString(start,h_adress.getPostCode());
        stat.setLong(start+1,h_adress.getStreet().getStreetCode());
        stat.setString(start +2,h_adress.getBuilding());
        stat.setString(start +3,h_adress.getExtension());
        stat.setString(start +4,h_adress.getApartment());
    }


    @Override
    public List<StudentOrder> getStudentOrders() throws DaoException {
        return getStudentOrderTwoSelect();
    }

    private List<StudentOrder> getStudentOrderTwoSelect() throws DaoException {
        List<StudentOrder>result=new LinkedList<>();
        try (Connection connection = getConnection();
             PreparedStatement stat = connection.prepareStatement(SELECT_ORDERS)){
             stat.setInt(1,StudentOrderStatus.START.ordinal());
             stat.setInt(2,limit);
             ResultSet rs=stat.executeQuery();
             while(rs.next()){
                StudentOrder so=getFullStudentOrder(rs);
                result.add(so);
             }
             findChildren(connection,result);
             rs.close();
        } catch(SQLException e){
            logger.error(e.getMessage(),e);
            throw new DaoException();
        }
        return result;
    }



    private List<StudentOrder> getStudentOrderOneSelect() throws DaoException {
        List<StudentOrder>result=new LinkedList<>();
        try (Connection connection = getConnection();
             PreparedStatement stat = connection.prepareStatement(SELECT_ORDERS_FULL)){
            Map<Long,StudentOrder>map=new HashMap<>();

            stat.setInt(1,StudentOrderStatus.START.ordinal());
            stat.setInt(2,limit);
            ResultSet rs=stat.executeQuery();
            int counter=0;
            while(rs.next()){
                Long soId=rs.getLong("student_order_id");
                if(!map.containsKey(soId)) {
                    StudentOrder so=getFullStudentOrder(rs);
                    result.add(so);
                    map.put(soId,so);
                }
                StudentOrder so=map.get(soId);
                so.addChild(fillChild(rs));
                //counter++;
            }
            if(counter>=limit){
                result.remove(result.size()-1);
            }
            //findChildren(connection,result);
            rs.close();
        } catch(SQLException e){
            e.printStackTrace();
            throw new DaoException();
        }
        return result;
    }

    private StudentOrder getFullStudentOrder(ResultSet rs) throws SQLException {
        StudentOrder so = new StudentOrder();
        fillStudentOrder(rs, so);
        fillMarriage(rs, so);
        so.setHusband(fillAdult(rs, "h_"));
        so.setWife(fillAdult(rs, "w_"));
        return so;
    }

    private void findChildren(Connection connection, List<StudentOrder> result) throws SQLException {
        String cl=" ("+result.stream().map(so -> String.valueOf(so.getStudentId())).collect(Collectors.joining(","))+")";
        Map<Long, StudentOrder> maps = result.stream().collect(Collectors.toMap(so -> so.getStudentId(), so -> so));
        try(PreparedStatement stat=connection.prepareStatement(SELECT_CHILD+cl)){
             ResultSet rs=stat.executeQuery();
             while(rs.next()){
                 Child ch= fillChild(rs);
                 StudentOrder so=maps.get(rs.getLong("student_order_id"));
                 so.addChild(ch);
             }
         }
    }



    private Adult fillAdult(ResultSet rs, String prefix) throws SQLException {
        Adult adult=new Adult();
        adult.setSurName(rs.getString(prefix+"sur_name"));
        adult.setGiveName(rs.getString(prefix+"given_name"));
        adult.setPatronymic(rs.getString(prefix+"patronymic"));
        adult.setDateOfBith(rs.getDate(prefix+"date_of_birth").toLocalDate());
        adult.setPassportSeria(rs.getString(prefix+"passport_seria"));
        adult.setPassportNumber(rs.getString(prefix+"pasport_number"));
        adult.setIssueDate(rs.getDate(prefix+"passport_date").toLocalDate());
        Long poId=rs.getLong(prefix + "passport_ofice_id");
        String poArea=rs.getString(prefix + "p_office_area_id");
        String poName=rs.getString(prefix + "p_office_name");
        PassportOffice office=new PassportOffice(poId,poArea,poName);

        adult.setIssueDepartment(office);

        Adress adress=new Adress();
        adress.setPostCode(rs.getString(prefix+"post_index"));
        adress.setStreet(new Street((rs.getLong(prefix+"street_code")),""));
        adress.setBuilding(rs.getString(prefix+"bulding"));
        adress.setExtension(rs.getString(prefix+"extension"));
        adress.setApartment(rs.getString(prefix+"apartament"));
        adult.setAdress(adress);

        adult.setUniversity(new University(rs.getLong(prefix+"university_id"),""));
        adult.setStudentId(rs.getString(prefix+"student_number"));
        return adult;
    }

    private void fillStudentOrder(ResultSet rs, StudentOrder so) throws SQLException {
        so.setStudentId(rs.getLong("student_order_id"));
        so.setStudentOrderDate(rs.getTimestamp("student_order_date").toLocalDateTime());
        so.setStudentOrderStatus(StudentOrderStatus.fromValue(rs.getInt("student_order_status")));

    }

    private void fillMarriage(ResultSet rs, StudentOrder so) throws SQLException {
        so.setMarriageSertificated(rs.getLong("certificate_id"));
        so.setMarriageDate(rs.getDate("marriage_date").toLocalDate());
        Long roId=rs.getLong("register_oficce_id");
        String area_id=rs.getString("r_office_area_id");
        String name=rs.getString("r_office_name");
        RegisterOffice ro=new RegisterOffice(roId,area_id, name);
        so.setMarriageOffice(ro);
    }

    private Child fillChild(ResultSet rs) throws SQLException{
        String surName=rs.getString("c_sur_name");
        String givenName=rs.getString("c_given_name");
        String patronymic=rs.getString("c_patronymic");
        LocalDate dateOfBith=rs.getDate("c_date_of_birth").toLocalDate();
        Child child=new Child(surName,givenName,patronymic,dateOfBith);
        child.setCertificateNumber(rs.getString("c_certificate_number"));
        child.setIssueDate(rs.getDate("c_certificate_date").toLocalDate());
        Long roId=rs.getLong("c_register_office_id");
        String roArea=rs.getString("r_office_area_id");
        String roName=rs.getString("r_office_name");
        RegisterOffice ro=new RegisterOffice(roId,roArea,roName);
        child.setIssueDepartment(ro);

        Adress adress=new Adress();
        adress.setPostCode(rs.getString("c_post_index"));
        adress.setStreet(new Street((rs.getLong("c_street_code")),""));
        adress.setBuilding(rs.getString("c_bulding"));
        adress.setExtension(rs.getString("c_extension"));
        adress.setApartment(rs.getString("c_apartament"));
        child.setAdress(adress);

        return child;
    }
}

