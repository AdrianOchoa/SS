package com.vw.util;

import com.vw.model.Criteria;
import com.vw.model.DetailedResultSet;
import com.vw.model.ResultSetForTotalClaims;
import com.vw.model.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.text.DecimalFormat;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * This class will manage the connection to the data base and will perform all
 * the querys to it.
 *
 * @author Adrián Ochoa Martínez
 */
public class DataBaseHelper {

    private final String dbURL;
    private Connection connection;
    private Statement statement;

    /**
     * this constructor builds the url to the data base
     */
    public DataBaseHelper() {
        dbURL = "jdbc:sqlserver://VW-PC\\SQLEXPRESS:1433;databaseName=criterios_logicos;user=sa;password=DinamoKof1#";
    }

    /**
     * this method loads the class and gets the connection
     *
     * @return true if the connectios succeds, false otherwise
     */
    public boolean getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = (Connection) DriverManager.getConnection(dbURL);
            return true;
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }

    //USERS METHODS
    /**
     * This method is used in the LoginServlet
     *
     * @param userName
     * @param password
     * @return if there's an user with this password, returns the user. Null
     * otherwise
     */
    public Usuario userExists(String userName, String password) {
        String queryContent = "USE criterios_logicos\n"
                + "SELECT [userName]\n"
                + "      ,[rol]\n"
                + "      ,[password]\n"
                + "FROM [criterios_logicos].[dbo].[usuario]\n"
                + "WHERE userName = '" + userName + "'\n"
                + "AND password = '" + password + "'\n"
                + "AND estatus = 'activo'";
        ResultSet resultSet = resultSetFromQuery(queryContent);
        Usuario usuario = null;
        try {
            while (resultSet.next()) {
                usuario = new Usuario();
                usuario.setUserName(resultSet.getString("userName"));
                usuario.setPassword(resultSet.getString("password"));
                usuario.setRol(resultSet.getString("rol"));
                return usuario;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return usuario;
    }

    /**
     * This method creates a new user for the system It's used in the userAdd
     * jsp.
     *
     * @param userName
     * @param password
     * @param rol
     * @param nombre
     * @param apP
     * @param apM
     * @param email
     * @param estatus
     * @return true if the user was created, false otherwise
     */
    public boolean createUser(String userName, String password, String rol,
            String nombre, String apP, String apM, String email, String estatus) {
        String tipo = (rol.trim().toLowerCase().equals("administrador")) ? "admin" : "user";
        String queryContent = "USE criterios_logicos\n"
                + "INSERT INTO [criterios_logicos].[dbo].[usuario]\n"
                + "           ([userName]\n"
                + "           ,[rol]\n"
                + "           ,[password]\n"
                + "           ,[nombre]\n"
                + "           ,[apellido_paterno]\n"
                + "           ,[apellido_materno]\n"
                + "           ,[email]\n"
                + "           ,[estatus])\n"
                + "     VALUES\n"
                + "           ('" + userName + "'"
                + "           ,'" + tipo + "'"
                + "           ,'" + password + "'"
                + "           ,'" + nombre + "'"
                + "           ,'" + apP + "'"
                + "           ,'" + apM + "'"
                + "           ,'" + email + "'"
                + "           ,'" + estatus + "')";
        return executeQuery(queryContent, false);
    }

    /**
     * This method get the usernames of the active users. It's used in the
     * deleteUser jsp.
     *
     * @param userID
     * @return a list with just the usernames of the active users of the system
     */
    public List<String> getActiveUserNames(String userID) {
        List<String> users = null;
        String queryContent = "USE criterios_logicos\n"
                + "SELECT [userName]\n"
                + "FROM [criterios_logicos].[dbo].[usuario]\n"
                + "WHERE estatus = 'activo'\n"
                + "AND [userName] != '" + userID + "'";
        ResultSet resultSet = resultSetFromQuery(queryContent);
        try {
            users = new ArrayList<String>();
            while (resultSet.next()) {
                users.add(resultSet.getString(1));
            }
            return users;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return users;
    }

    /**
     * This method get the usernames of the all the users. It's used in the
     * editUser jsp.
     *
     * @return a list with just the usernames of the all users of the system
     */
    public List<String> getAllUserNames() {
        List<String> users = null;
        String queryContent = "USE criterios_logicos\n"
                + "SELECT [userName]\n"
                + "FROM [criterios_logicos].[dbo].[usuario]";
        ResultSet resultSet = resultSetFromQuery(queryContent);
        try {
            users = new ArrayList<String>();
            while (resultSet.next()) {
                users.add(resultSet.getString(1));
            }
            return users;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return users;
    }

    /**
     * this method deactivates a specific user from the system It's used in the
     * userDelete jsp.
     *
     * @param userName
     * @return true if the user was delete, false otherwise
     */
    public boolean deleteUserByUserName(String userName) {
        String queryContent = "USE criterios_logicos\n"
                + "UPDATE [criterios_logicos].[dbo].[usuario]\n"
                + "SET [estatus] = 'inactivo'\n"
                + " WHERE [userName] = '" + userName + "'";
        return executeQuery(queryContent, false);
    }

    //CRITERIA METHODS
    /**
     * This method adds a new criteria to the system It's used in the
     * criteriaAdd jsp/CriteriaAddServlet.
     *
     * @param id
     * @param idNuevo
     * @param idViejo
     * @param estatus
     * @param departamento
     * @param tipo
     * @param nivel
     * @param objetivo
     * @param grupo
     * @param contenido
     * @param comentario
     * @param datos
     * @param averia
     * @param danio
     * @param marca
     * @param claveComercial
     * @param modelo
     * @param tiposGarantia
     * @param solicitante
     * @param fechaCreacion
     * @param fechaRevision
     * @param periodo
     * @param agregadoPor
     * @return true if the criteria was added, false otherwise
     */
    public boolean addCriteria(String id, String idNuevo, String idViejo,
            String estatus, String departamento, String tipo, String nivel, String objetivo,
            String grupo, String contenido, String comentario, String datos, String averia,
            String danio, String marca, String claveComercial, String modelo, String tiposGarantia,
            String solicitante, String fechaCreacion, String fechaRevision, int periodo, String agregadoPor) {
        String queryContent = "INSERT INTO [criterios_logicos].[dbo].[criterio]\n"
                + "           ([criterio_ID]\n"
                + "           ,[criterio_id_nuevo]\n"
                + "           ,[criterio_id_viejo]\n"
                + "           ,[criterio_estatus]\n"
                + "           ,[criterio_departamento]\n"
                + "           ,[criterio_tipo]\n"
                + "           ,[criterio_nivel]\n"
                + "           ,[criterio_objetivo]\n"
                + "           ,[criterio_grupo]\n"
                + "           ,[criterio_contenido]\n"
                + "           ,[criterio_comentario]\n"
                + "           ,[criterio_datos_a_detener]\n"
                + "           ,[criterio_averia]\n"
                + "           ,[criterio_danio]\n"
                + "           ,[criterio_marca]\n"
                + "           ,[criterio_tipo_auto_clave_comercial]\n"
                + "           ,[criterio_anio_modelo]\n"
                + "           ,[criterio_tipos_garantia_afecta]\n"
                + "           ,[criterio_solicitante]\n"
                + "           ,[criterio_fecha_creacion]\n"
                + "           ,[criterio_fecha_revision]\n"
                + "           ,[criterio_periodo_revision]\n"
                + "           ,[criterio_agregado_por]\n"
                + "           ,[criterio_aprobado])\n"
                + "     VALUES\n"
                + "           ('" + id + "'\n"
                + "           ,'" + idNuevo + "'\n"
                + "           ,'" + idViejo + "'\n"
                + "           ,'" + estatus + "'\n"
                + "           ,'" + departamento + "'\n"
                + "           ,'" + tipo + "'\n"
                + "           ,'" + nivel + "'\n"
                + "           ,'" + objetivo + "'\n"
                + "           ,'" + grupo + "'\n"
                + "           ,'" + contenido + "'\n"
                + "           ,'" + comentario + "'\n"
                + "           ,'" + datos + "'\n"
                + "           ,'" + averia + "'\n"
                + "           ,'" + danio + "'\n"
                + "           ,'" + marca + "'\n"
                + "           ,'" + claveComercial + "'\n"
                + "           ,'" + modelo + "'\n"
                + "           ,'" + tiposGarantia + "'\n"
                + "           ,'" + solicitante + "'\n"
                + "           ,'" + fechaCreacion + "'\n"
                + "           ,'" + fechaRevision + "'\n"
                + "           ," + periodo + "\n"
                + "           ,'" + agregadoPor + "'\n"
                + "           ,'n')";
        return executeQuery(queryContent, false);
    }

    /**
     * This method updates a specific criteria from the system It's used in the
     * editCriteriaForm jsp/CriteriaEditServlet
     *
     * @param ID
     * @param estatus
     * @param departamento
     * @param tipo
     * @param nivel
     * @param objetivo
     * @param grupo
     * @param contenido
     * @param comentario
     * @param datos
     * @param averia
     * @param danio
     * @param marca
     * @param claveComercial
     * @param modelo
     * @param garantiaAfecta
     * @param fecha
     * @return true if the system was edited, false otherwise
     */
    public boolean editCriteria(String ID, String estatus, String departamento,
            String tipo, String nivel, String objetivo, String grupo, String contenido,
            String comentario, String datos, String averia, String danio, String marca,
            String claveComercial, String modelo, String garantiaAfecta, String fecha) {
        String queryContent = "UPDATE criterios_logicos.dbo.criterio\n"
                + "SET [criterio_estatus] = '" + estatus + "'\n"
                + ",[criterio_departamento] = '" + departamento + "'\n"
                + ",[criterio_tipo] = '" + tipo + "'\n"
                + ",[criterio_nivel] = '" + nivel + "'\n"
                + ",[criterio_objetivo] = '" + objetivo + "'\n"
                + ",[criterio_grupo] = '" + grupo + "'\n"
                + ",[criterio_contenido] = '" + contenido + "'\n"
                + ",[criterio_comentario] = '" + comentario + "'\n"
                + ",[criterio_datos_a_detener] = '" + datos + "'\n"
                + ",[criterio_averia] = '" + averia + "'\n"
                + ",[criterio_danio] = '" + danio + "'\n"
                + ",[criterio_marca] = '" + marca + "'\n"
                + ",[criterio_tipo_auto_clave_comercial] = '" + claveComercial + "'\n"
                + ",[criterio_anio_modelo] = '" + modelo + "'\n"
                + ",[criterio_tipos_garantia_afecta] = '" + garantiaAfecta + "'\n"
                + ",[criterio_fecha_revision] = '" + fecha + "'\n"
                + " WHERE [criterio_ID] = '" + ID + "'";
        return executeQuery(queryContent, false);
    }

    /**
     * It's used in the criteriaList jsp/CriteriaGetCriteriaServlet
     *
     * @return a list with the full info of all criteria
     */
    public List<Criteria> getAllFromCriteria() {
        List<Criteria> criterios = new ArrayList();
        String queryContent = "USE criterios_logicos\n"
                + "SELECT *\n"
                + "FROM criterios_logicos.dbo.criterio";
        ResultSet resultSet = resultSetFromQuery(queryContent);
        try {
            while (resultSet.next()) {
                Criteria criterio = new Criteria();
                criterio.setCriterioID(resultSet.getString(1));
                criterio.setIdNuevo(resultSet.getString(2));
                criterio.setIdViejo(resultSet.getString(3));
                criterio.setEstatus(resultSet.getString(4));
                criterio.setDepartamento((resultSet.getString(5)));
                criterio.setTipo(resultSet.getString(6));
                criterio.setNivel(resultSet.getString(7));
                criterio.setObjetivo(resultSet.getString(8));
                criterio.setGrupo(resultSet.getString(9));
                criterio.setContenido(resultSet.getString(10));
                criterio.setComentario(resultSet.getString(11));
                criterio.setDatosDetener(resultSet.getString(12));
                criterio.setAveria(resultSet.getString(13));
                criterio.setDanio(resultSet.getString(14));
                criterio.setMarca(resultSet.getString(15));
                criterio.setClaveComercial(resultSet.getString(16));
                criterio.setModelo(resultSet.getString(17));
                criterio.setGarantiaAfecta(resultSet.getString(18));
                criterio.setSolicitante(resultSet.getString(19));
                criterio.setFechaCreacion(resultSet.getString(20));
                criterio.setFechaRevision(resultSet.getString(21));
                criterio.setPeriodoRevision(Integer.toString(resultSet.getInt(22)));
                criterio.setAgregadoPor(resultSet.getString(23));
                criterios.add(criterio);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return criterios;
    }

    /**
     * This methods return just the editable info of an especific criteria. It's
     * used in the criteriaEdit jsp/CriteriaSendInfoEditServlet.
     *
     * @param ID
     * @return
     */
    public Criteria getEditableInfoByCriteriaID(String ID) {
        Criteria criterio = null;
        String queryContent = "SELECT [criterio_estatus]\n"
                + "      ,[criterio_departamento]\n"
                + "      ,[criterio_tipo]\n"
                + "      ,[criterio_nivel]\n"
                + "      ,[criterio_objetivo]\n"
                + "      ,[criterio_contenido]\n"
                + "      ,[criterio_comentario]\n"
                + "      ,[criterio_datos_a_detener]\n"
                + "      ,[criterio_averia]\n"
                + "      ,[criterio_danio]\n"
                + "      ,[criterio_marca]\n"
                + "      ,[criterio_tipo_auto_clave_comercial]\n"
                + "      ,[criterio_anio_modelo]\n"
                + "      ,[criterio_tipos_garantia_afecta]\n"
                + "  FROM [criterios_logicos].[dbo].[criterio]\n"
                + "  WHERE criterio_ID = '" + ID + "'\n";
        ResultSet resultSet = resultSetFromQuery(queryContent);
        try {
            while (resultSet.next()) {
                criterio = new Criteria();
                criterio.setEstatus(resultSet.getString("criterio_estatus"));
                criterio.setDepartamento(resultSet.getString("criterio_departamento"));
                criterio.setTipo(resultSet.getString("criterio_tipo"));
                criterio.setNivel(resultSet.getString("criterio_nivel"));
                criterio.setObjetivo(resultSet.getString("criterio_objetivo"));
                criterio.setContenido(resultSet.getString("criterio_contenido"));
                criterio.setComentario(resultSet.getString("criterio_comentario"));
                criterio.setDatosDetener(resultSet.getString("criterio_datos_a_detener"));
                criterio.setAveria(resultSet.getString("criterio_averia"));
                criterio.setDanio(resultSet.getString("criterio_danio"));
                criterio.setMarca(resultSet.getString("criterio_marca"));
                criterio.setClaveComercial(resultSet.getString("criterio_tipo_auto_clave_comercial"));
                criterio.setModelo(resultSet.getString("criterio_anio_modelo"));
                criterio.setGarantiaAfecta(resultSet.getString("criterio_tipos_garantia_afecta"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return criterio;
    }

    /**
     * This method will get the the id's of the non-approved criteria. It's used
     * in the criteriaToBeApproved jsp/CriteriaGetNotApprovedCriteriaServlet
     *
     * @return a list with the id's of the non approved criteria.
     */
    public List<String> getNotApprovedCriteria() {
        String queryContent = "USE criterios_logicos\n"
                + "SELECT criterio_ID\n"
                + "FROM criterios_logicos.dbo.criterio\n"
                + "WHERE criterio_aprobado_negocio = 'n'";
        ResultSet resultSet = resultSetFromQuery(queryContent);
        List<String> criterios = new ArrayList<String>();
        try {
            while (resultSet.next()) {
                criterios.add(resultSet.getString(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return criterios;
    }

    /**
     * this method will get the the id's of the active criteria. It's used in
     * the criteriaDelete jsp/CriteriaGetCriteriaByIDsServlet
     *
     * @return a list with the id's of the active criteria.
     */
    public List<String> getActiveCriteriaByID() {
        String queryContent = "USE criterios_logicos\n"
                + "SELECT criterio_ID\n"
                + "FROM criterios_logicos.dbo.criterio\n"
                + "WHERE criterio_estatus = 'activo'\n"
                + "AND criterio_aprobado_negocio = 's'\n"
                + "and criterio_aprobado_admin = 's'";
        ResultSet resultSet = resultSetFromQuery(queryContent);
        List<String> criterios = new ArrayList<String>();
        try {
            while (resultSet.next()) {
                criterios.add(resultSet.getString(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return criterios;
    }

    /**
     * this method will get the the id's of the all the criteria. It's used in
     * the criteriaEdit jsp/CriteriaGetEditableCriteriaServlet
     *
     * @return a list with the id's of the active criteria.
     */
    public List<String> getAllCriteriaByID() {
        String queryContent = "USE criterios_logicos\n"
                + "SELECT criterio_ID\n"
                + "FROM criterios_logicos.dbo.criterio\n"
                + "where criterio_aprobado_negocio = 's'"
                + "and criterio_aprobado_admin = 's'";
        ResultSet resultSet = resultSetFromQuery(queryContent);
        List<String> criterios = new ArrayList<String>();
        try {
            while (resultSet.next()) {
                criterios.add(resultSet.getString(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return criterios;
    }

    /**
     * This method will deactivate an especific criteria It's used in the
     * criteriaDelete jsp/CriteriaDeleteServlet.
     *
     * @param id
     * @return true if the criteria was successfully deactivated, false
     * otherwise.
     */
    public boolean deactivateCriteriaByID(String id) {
        String queryContent = "USE criterios_logicos\n"
                + "UPDATE [criterios_logicos].[dbo].[criterio]\n"
                + "   SET [criterio_estatus] = 'inactivo'\n"
                + " WHERE [criterio_ID] = '" + id + "'";
        return executeQuery(queryContent, false);
    }

    //REPORTING METHODS
    public List<DetailedResultSet> getDetailedReport(String criteriaID, String reportType,
            String initialDate, String finalDate, String solicitante) {
        List<DetailedResultSet> result = new ArrayList<DetailedResultSet>();
        if (reportType.equals("ajusteAnalista")) {
            result = getDetailedReportFromAnalistAdjust(criteriaID, initialDate, finalDate, solicitante);
        } else if (reportType.equals("ajusteCriterio")) {
            result = getDetailedReportFromCriteriaAdjust(criteriaID, initialDate, finalDate, solicitante);
        } else if (reportType.equals("canceladosAnalista")) {
            result = getDetailedReportFromAnalistCanceled(criteriaID, initialDate, finalDate, solicitante);
        } else if (reportType.equals("canceladosCriterio")) {
            result = getDetailedReportFromCriteriaCanceled(criteriaID, initialDate, finalDate, solicitante);
        }
        return result;
    }

    private List<DetailedResultSet> getDetailedReportFromAnalistCanceled(String criteriaID,
            String initialDate, String finalDate, String solicitante) {
        List<DetailedResultSet> result = new ArrayList();

        //Here is a list with all the intelligent criteria
        LinkedHashMap<String, String> regularCriteria = getRegularCriteria();

        //Here is a map which contains all the claims from claim file and the respective criteria ID
        LinkedHashMap<String, String> originalClaims = getAllClaimsFromRoc(initialDate, finalDate);

        //The new data is gonna be saved here
        LinkedHashMap<String, String> filteredClaims = new LinkedHashMap();

        //Now we're gonna filters the claims affeected by an intelligent criteria
        for (Map.Entry<String, String> entry : originalClaims.entrySet()) {
            String criteria = entry.getValue();
            String[] criteriaIDs = criteria.split("[*]+");
            for (String criteriaID1 : criteriaIDs) {
                if (regularCriteria.containsKey(criteriaID1) || regularCriteria.containsValue(criteriaID1)) {
                    filteredClaims.put(entry.getKey(), criteriaID1);
                }
            }
        }

        //At this point, we have all the claims from the claim criteria that were affected by an intelligent criteria
        //Now, we're gonna get all tha claims from the claim criteria
        HashMap<String, String> claimCriteriaClaims = getClaimCriteriaClaims(initialDate, finalDate);

        //Now, we're gonna get all the claims from the data warehouse
        HashMap<String, String> dwhClaims = getDwhClaims(initialDate, finalDate);

        //Now, we're gonna get all the claims from the easy
        HashMap<String, String> easyClaims = getEasyClaims(initialDate, finalDate);

        for (Map.Entry<String, String> entry : filteredClaims.entrySet()) {
            String rocClaimID = entry.getKey();
            String criteria = entry.getValue();
            if (claimCriteriaClaims.containsKey(rocClaimID)) {
                continue;
            }
            if (dwhClaims.containsKey(rocClaimID)) {
                continue;
            }
            if (easyClaims.containsKey(rocClaimID)) {
                continue;
            }
            if (criteria.equals(criteriaID)) {
                result.add(getDetailedResultSetFromRoc(rocClaimID, solicitante, criteriaID));
            }
        }
        return result;
    }

    private List<DetailedResultSet> getDetailedReportFromCriteriaCanceled(String criteriaID,
            String initialDate, String finalDate, String solicitante) {
        //Here is gonna be the final result
        List<DetailedResultSet> result = new ArrayList();

        //Here is a list with all the intelligent criteria
        LinkedHashMap<String, String> intelligentCriteria = getIntelligentCriteria();

        //Here is a map which contains all the claims from claim file and the respective criteria ID
        LinkedHashMap<String, String> originalClaims = getAllClaimsFromClaimCriteria(initialDate, finalDate);

        //The new data is gonna be saved here
        LinkedHashMap<String, String> filteredClaims = new LinkedHashMap();

        //Now we're gonna filters the claims affeected by an intelligent criteria
        for (Map.Entry<String, String> entry : originalClaims.entrySet()) {
            String idCriteria = entry.getValue();
            if (intelligentCriteria.containsKey(idCriteria)
                    || intelligentCriteria.containsValue(idCriteria)) {
                filteredClaims.put(entry.getKey(), idCriteria);
            }
        }

        //At this point, we have all the claims from the claim criteria that were affected by an intelligent criteria
        //Now, we're gonna get all tha claims from the roc 
        HashMap<String, String> rocClaims = getRocClaims(initialDate, finalDate);

        //Now, we're gonna get all the claims from the data warehouse
        HashMap<String, String> dwhClaims = getDwhClaims(initialDate, finalDate);

        //Now, we're gonna get all the claims from the easy
        HashMap<String, String> easyClaims = getEasyClaims(initialDate, finalDate);

        for (Map.Entry<String, String> entry : filteredClaims.entrySet()) {
            String claimCriteriaClaimID = entry.getKey();
            String idCriteria;
            if (rocClaims.containsKey(claimCriteriaClaimID)) {
                continue;
            }
            if (dwhClaims.containsKey(claimCriteriaClaimID)) {
                continue;
            }
            if (easyClaims.containsKey(claimCriteriaClaimID)) {
                continue;
            }
            idCriteria = entry.getValue();
            if (idCriteria.equals(criteriaID)) {
                DetailedResultSet resultSet = getDetailedResultSetFromClaimCriteria(
                        claimCriteriaClaimID, solicitante, criteriaID, "roc");
                result.add(resultSet);
            }
        }

        Collections.sort(result);

        return result;
    }

    private DetailedResultSet getDetailedResultSetFromRoc(String idClaim,
            String solicitante, String criteriaID, String table) {
        String id = idClaim;
        String monto = getClaimAmountFromRocDWH(idClaim);
        String chasis = getClaimChasis(idClaim);
        String claimSerial = getClaimSerial(idClaim);
        String dealer = getClaimDealer(idClaim);
        return new DetailedResultSet(id, monto, solicitante, chasis, criteriaID, claimSerial, dealer);
    }

    private DetailedResultSet getDetailedResultSetFromClaimCriteria(String idClaim,
            String solicitante, String criteriaID, String table) {
        String id = idClaim;
        String monto = (table.equals("roc")) ? getClaimAmountFromRoc(idClaim) : getClaimAmountFromDWH(idClaim);
        String chasis = getClaimChasis(idClaim);
        String claimSerial = getClaimSerial(idClaim);
        String dealer = getClaimDealer(idClaim);
        return new DetailedResultSet(id, monto, solicitante, chasis, criteriaID, claimSerial, dealer);
    }

    private DetailedResultSet getDetailedResultSetFromRoc(String idClaim,
            String solicitante, String criteriaID) {
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);
        String monto = "$" + decimalFormat.format(getAmountFromRoc(idClaim));
        String chasis = getChasisFromRoc(idClaim);
        String serial = getSerialFromRoc(idClaim);
        String dealer = getDealerFromRoc(idClaim);
        return new DetailedResultSet(idClaim, monto, solicitante, chasis, criteriaID, serial, dealer);
    }

    /**
     * This method will process claim criteria/roc/data warehouse claims and get
     * the necessary info about this claims
     *
     * @param initialDate
     * @param finalDate
     * @return a list with the regular criteria and the claims afected by it
     */
    public List<com.vw.model.ResultSet> getAdjustByAnalist(String initialDate,
            String finalDate) {
        //Here is gonna be the final result
        List<com.vw.model.ResultSet> result = new ArrayList();
        HashMap<String, com.vw.model.ResultSet> auxResult = new HashMap();

        //Here is a list with all the intelligent criteria
        LinkedHashMap<String, String> regularCriteria = getRegularCriteria();

        //Here is a map which contains all the claims from claim file and the respective criteria ID
        LinkedHashMap<String, String> originalClaims = getAllClaimsFromRoc(initialDate, finalDate);

        //The new data is gonna be saved here
        LinkedHashMap<String, String> filteredClaims = new LinkedHashMap();

        //Now we're gonna filters the claims affeected by a regular criteria
        for (Map.Entry<String, String> entry : originalClaims.entrySet()) {
            String criteriaIDFromOriginalClaim = entry.getValue();
            String ids[] = criteriaIDFromOriginalClaim.split("[*]+");
            for (int i = 0; i < ids.length; i++) {
                if (regularCriteria.containsKey(ids[i])) {
                    String claimIDFromOriginalClaim = entry.getKey();
                    filteredClaims.put(claimIDFromOriginalClaim, ids[i]);
                } else if (regularCriteria.containsValue(ids[i])) {
                    String claimIDFromOriginalClaim = entry.getKey();
                    filteredClaims.put(claimIDFromOriginalClaim, ids[i]);
                }
            }
        }


        //Now, we're gonna get all the claims from the data warehouse
        HashMap<String, String> dwhClaims = getDwhClaims(initialDate, finalDate);

        HashMap<String, Double> amountsByIdCriteria = new HashMap();

        /*Now we're gonna compare the filterecClaims from claim criteria to 
         the claims from roc and datawarehouse*/
        for (Map.Entry<String, String> entry : filteredClaims.entrySet()) {
            String claimIDFromFilteredClaims = entry.getKey();
            String criteriaIDFromFilteredClaims = entry.getValue();
            if (dwhClaims.containsKey(claimIDFromFilteredClaims)) {
                if (!auxResult.containsKey(criteriaIDFromFilteredClaims)) {
                    com.vw.model.ResultSet resultSet = getResultSetFromDataWarehouse(
                            false, criteriaIDFromFilteredClaims,
                            filteredClaims, dwhClaims);
                    double amount = getAmountFromRoc(
                            claimIDFromFilteredClaims) - getAmountFromDWH(claimIDFromFilteredClaims);
                    double d;
                    try {
                        d = amountsByIdCriteria.get(criteriaIDFromFilteredClaims);
                    } catch (NullPointerException ex) {
                        d = 0.0;
                    }
                    amountsByIdCriteria.put(criteriaIDFromFilteredClaims, d + amount);
                    auxResult.put(criteriaIDFromFilteredClaims, resultSet);
//                    result.add(resultSet);
                    System.out.println("ResulSet from dwh " + criteriaIDFromFilteredClaims);
                } else {
                    double amount = getAmountFromRoc(
                            claimIDFromFilteredClaims) - getAmountFromDWH(claimIDFromFilteredClaims);
                    double d;
                    try {
                        d = amountsByIdCriteria.get(criteriaIDFromFilteredClaims);
                    } catch (NullPointerException ex) {
                        d = 0.0;
                    }
                    amountsByIdCriteria.put(criteriaIDFromFilteredClaims, d + amount);
                }
            }
        }

        for (Map.Entry<String, com.vw.model.ResultSet> entry : auxResult.entrySet()) {
            String criteriaID = entry.getKey();
            com.vw.model.ResultSet res = entry.getValue();
            DecimalFormat decimalFormat = new DecimalFormat();
            decimalFormat.setMaximumFractionDigits(2);
            String amount = "$" + decimalFormat.format(amountsByIdCriteria.get(criteriaID));
            res.setMonto(amount);
            auxResult.put(criteriaID, res);
        }

        for (Map.Entry<String, com.vw.model.ResultSet> entry : auxResult.entrySet()) {
            result.add(entry.getValue());
        }

        Collections.sort(result);

        return result;
    }

    /**
     * This method will process claim criteria/roc/data warehouse claims and get
     * the necessary info about this claims
     *
     * @param initialDate
     * @param finalDate
     * @return a list with the intelligent criteria and the claims afected by it
     */
    public List<com.vw.model.ResultSet> getAdjustByCriteria(String initialDate,
            String finalDate) {
        //Here is gonna be the final result
        List<com.vw.model.ResultSet> result = new ArrayList();
        HashMap<String, com.vw.model.ResultSet> auxResult = new HashMap();

        //Here is a list with all the intelligent criteria
        LinkedHashMap<String, String> intelligentCriteria = getIntelligentCriteria();

        //Here is a map which contains all the claims from claim file and the respective criteria ID
        LinkedHashMap<String, String> originalClaims = getAllClaimsFromClaimCriteria(initialDate, finalDate);

        //The new data is gonna be saved here
        LinkedHashMap<String, String> filteredClaims = new LinkedHashMap();

        //Now we're gonna filters the claims affeected by an intelligent criteria
        for (Map.Entry<String, String> entry : originalClaims.entrySet()) {
            String criteriaID = entry.getValue();
            if (intelligentCriteria.containsKey(criteriaID)
                    || intelligentCriteria.containsValue(criteriaID)) {
                filteredClaims.put(entry.getKey(), criteriaID);
            }
        }

        //At this point, we have all the claims from the claim criteria that were affected by an intelligent criteria
        //Now, we're gonna get all tha claims from the roc 
        HashMap<String, String> rocClaims = getRocClaims(initialDate, finalDate);

        //Now, we're gonna get all the claims from the data warehouse
        HashMap<String, String> dwhClaims = getDwhClaims(initialDate, finalDate);

        HashMap<String, Double> amountsByCriteria = new HashMap();

        /*Now we're gonna compare the filterecClaims from claim criteria to 
         the claims from roc and datawarehouse*/
        for (Map.Entry<String, String> entry : filteredClaims.entrySet()) {
            String claimFroClaimCriteriaID = entry.getKey();
            String criteriaID = entry.getValue();
            if (rocClaims.containsKey(claimFroClaimCriteriaID)) {
                if (!auxResult.containsKey(criteriaID)) {
                    com.vw.model.ResultSet resultSet = getResultSetFromRoc(true, criteriaID,
                            filteredClaims, rocClaims);
//                    result.add(resultSet);
                    double amount = getAmountFromClaimCriteria(claimFroClaimCriteriaID) - getAmountFromRoc(claimFroClaimCriteriaID);
                    auxResult.put(criteriaID, resultSet);
                    double d;
                    try {
                        d = amountsByCriteria.get(criteriaID);
                    } catch (NullPointerException ex) {
                        d = 0.0;
                    }
                    amountsByCriteria.put(criteriaID, d + amount);
                    System.out.println("ResultSet from roc " + criteriaID);
                } else {
                    double amount = getAmountFromClaimCriteria(claimFroClaimCriteriaID) - getAmountFromRoc(claimFroClaimCriteriaID);
//                    auxResult.put(criteriaID, resultSet);
                    double d;
                    try {
                        d = amountsByCriteria.get(criteriaID);
                    } catch (NullPointerException ex) {
                        d = 0.0;
                    }
                    amountsByCriteria.put(criteriaID, d + amount);
                }
            } else if (dwhClaims.containsKey(claimFroClaimCriteriaID)) {
                if (!auxResult.containsKey(criteriaID)) {
                    com.vw.model.ResultSet resultSet = getResultSetFromDataWarehouse(true, criteriaID,
                            filteredClaims, dwhClaims);
//                    result.add(resultSet);
                    double amount = getAmountFromClaimCriteria(claimFroClaimCriteriaID) - getAmountFromDWH(claimFroClaimCriteriaID);
                    auxResult.put(criteriaID, resultSet);
                    double d;
                    try {
                        d = amountsByCriteria.get(criteriaID);
                    } catch (NullPointerException ex) {
                        d = 0.0;
                    }
                    amountsByCriteria.put(criteriaID, d + amount);
                    auxResult.put(criteriaID, resultSet);
                    System.out.println("ResultSet from dwh " + criteriaID);
                } else {
                    double amount = getAmountFromClaimCriteria(claimFroClaimCriteriaID) - getAmountFromDWH(claimFroClaimCriteriaID);
//                    auxResult.put(criteriaID, resultSet);
                    double d;
                    try {
                        d = amountsByCriteria.get(criteriaID);
                    } catch (NullPointerException ex) {
                        d = 0.0;
                    }
                    amountsByCriteria.put(criteriaID, d + amount);
                }
            }
        }

        for (Map.Entry<String, com.vw.model.ResultSet> entry : auxResult.entrySet()) {
            String criteriaID = entry.getKey();
            com.vw.model.ResultSet res = entry.getValue();
            DecimalFormat decimalFormat = new DecimalFormat();
            decimalFormat.setMaximumFractionDigits(2);
            String amount = "$" + decimalFormat.format(amountsByCriteria.get(criteriaID));
            res.setMonto(amount);
            auxResult.put(criteriaID, res);
        }

        for (Map.Entry<String, com.vw.model.ResultSet> entry : auxResult.entrySet()) {
            result.add(entry.getValue());
        }

        Collections.sort(result);

        return result;
    }

    /**
     *
     * @param initialDate
     * @param finalDate
     * @return
     */
    public List<com.vw.model.ResultSet> getCanceledClaimsByCriteria(String initialDate, String finalDate) {
        //Here is gonna be the final result
        List<com.vw.model.ResultSet> result = new ArrayList();
        HashMap<String, com.vw.model.ResultSet> auxResult = new HashMap();

        //Here is a list with all the intelligent criteria
        LinkedHashMap<String, String> intelligentCriteria = getIntelligentCriteria();

        //Here is a map which contains all the claims from claim file and the respective criteria ID
        LinkedHashMap<String, String> originalClaims = getAllClaimsFromClaimCriteria(initialDate, finalDate);

        //The new data is gonna be saved here
        LinkedHashMap<String, String> filteredClaims = new LinkedHashMap();

        //Now we're gonna filters the claims affeected by an intelligent criteria
        for (Map.Entry<String, String> entry : originalClaims.entrySet()) {
            String criteriaID = entry.getValue();
            if (intelligentCriteria.containsKey(criteriaID)
                    || intelligentCriteria.containsValue(criteriaID)) {
                filteredClaims.put(entry.getKey(), criteriaID);
            }
        }

        //At this point, we have all the claims from the claim criteria that were affected by an intelligent criteria
        //Now, we're gonna get all tha claims from the roc 
        HashMap<String, String> rocClaims = getRocClaims(initialDate, finalDate);

        //Now, we're gonna get all the claims from the data warehouse
        HashMap<String, String> dwhClaims = getDwhClaims(initialDate, finalDate);

        //Now, we're gonna get all the claims from the easy
        HashMap<String, String> easyClaims = getEasyClaims(initialDate, finalDate);
        
        HashMap<String, Double> amountsByCriteria = new HashMap();

        for (Map.Entry<String, String> entry : filteredClaims.entrySet()) {
            String claimCriteriaClaimID = entry.getKey();
            String criteriaID;
            if (rocClaims.containsKey(claimCriteriaClaimID)) {
                continue;
            }
            if (dwhClaims.containsKey(claimCriteriaClaimID)) {
                continue;
            }
            if (easyClaims.containsKey(claimCriteriaClaimID)) {
                continue;
            }
            criteriaID = entry.getValue();
            if (!auxResult.containsKey(criteriaID)) {
                com.vw.model.ResultSet resultSet = getResultSetFromCanceledClaimCriteria(true, criteriaID, filteredClaims);
//                result.add(resultSet);
                double amount = getAmountFromClaimCriteria(claimCriteriaClaimID);
                double d;
                try {
                    d = amountsByCriteria.get(criteriaID);
                } catch (NullPointerException ex) {
                    d = 0.0;
                }
                amountsByCriteria.put(criteriaID, d + amount);
                auxResult.put(criteriaID, resultSet);
            } else {
                double amount = getAmountFromClaimCriteria(claimCriteriaClaimID);
                double d;
                try {
                    d = amountsByCriteria.get(criteriaID);
                } catch (NullPointerException ex) {
                    d = 0.0;
                }
                amountsByCriteria.put(criteriaID, d + amount);
            }
        }
        
        for(Map.Entry<String, com.vw.model.ResultSet> entry : auxResult.entrySet()) {
            String criteriaID = entry.getKey();
            com.vw.model.ResultSet res = entry.getValue();
            DecimalFormat decimalFormat = new DecimalFormat();
            decimalFormat.setMaximumFractionDigits(2);
            String amount = "$" + decimalFormat.format(amountsByCriteria.get(criteriaID));
            res.setMonto(amount);
            auxResult.put(criteriaID, res);
        }
        
        for(Map.Entry<String, com.vw.model.ResultSet> entry : auxResult.entrySet()) {
            result.add(entry.getValue());
        }

        Collections.sort(result);

        return result;
    }

    /**
     *
     * @param initialDate
     * @param finalDate
     * @return
     */
    public List<com.vw.model.ResultSet> getCanceledClaimsByAnalist(String initialDate,
            String finalDate) {
        //Here is gonna be the final result
        List<com.vw.model.ResultSet> result = new ArrayList();
        HashMap<String, com.vw.model.ResultSet> auxResult = new HashMap();

        //Here is a list with all the intelligent criteria
        LinkedHashMap<String, String> regularCriteria = getRegularCriteria();

        //Here is a map which contains all the claims from claim file and the respective criteria ID
        LinkedHashMap<String, String> originalClaims = getAllClaimsFromRoc(initialDate, finalDate);

        //The new data is gonna be saved here
        LinkedHashMap<String, String> filteredClaims = new LinkedHashMap();

        //Now we're gonna filters the claims affeected by an intelligent criteria
        for (Map.Entry<String, String> entry : originalClaims.entrySet()) {
            String criteriaID = entry.getValue();
            String[] criteriaIDs = criteriaID.split("[*]+");
            for (String criteriaID1 : criteriaIDs) {
                if (regularCriteria.containsKey(criteriaID1) || regularCriteria.containsValue(criteriaID1)) {
                    filteredClaims.put(entry.getKey(), criteriaID1);
                }
            }
        }

        //At this point, we have all the claims from the claim criteria that were affected by an intelligent criteria
        //Now, we're gonna get all tha claims from the claim criteria
        HashMap<String, String> claimCriteriaClaims = getClaimCriteriaClaims(initialDate, finalDate);

        //Now, we're gonna get all the claims from the data warehouse
        HashMap<String, String> dwhClaims = getDwhClaims(initialDate, finalDate);

        //Now, we're gonna get all the claims from the easy
        HashMap<String, String> easyClaims = getEasyClaims(initialDate, finalDate);
        
        HashMap<String, Double> amountsByCriteria = new HashMap();

        for (Map.Entry<String, String> entry : filteredClaims.entrySet()) {
            String claimCriteriaClaimID = entry.getKey();
            String criteriaID;
            if (claimCriteriaClaims.containsKey(claimCriteriaClaimID)) {
                continue;
            }
            if (dwhClaims.containsKey(claimCriteriaClaimID)) {
                continue;
            }
            if (easyClaims.containsKey(claimCriteriaClaimID)) {
                continue;
            }
            criteriaID = entry.getValue();
            if (!auxResult.containsKey(criteriaID)) {
                com.vw.model.ResultSet resultSet = getResultSetFromCanceledRoc(false, criteriaID, filteredClaims, claimCriteriaClaimID);
//                result.add(resultSet);
                double amount = getAmountFromRoc(claimCriteriaClaimID);
                double d;
                try {
                    d = amountsByCriteria.get(criteriaID);
                } catch (NullPointerException ex) {
                    d = 0.0;
                }
                amountsByCriteria.put(criteriaID, d + amount);
                auxResult.put(criteriaID, resultSet);
            } else {
                double amount = getAmountFromRoc(claimCriteriaClaimID);
                double d;
                try {
                    d = amountsByCriteria.get(criteriaID);
                } catch (NullPointerException ex) {
                    d = 0.0;
                }
                amountsByCriteria.put(criteriaID, d + amount);
            }
        }
        
        for(Map.Entry<String, com.vw.model.ResultSet> entry : auxResult.entrySet()) {
            String criteriaID = entry.getKey();
            com.vw.model.ResultSet res = entry.getValue();
            DecimalFormat decimalFormat = new DecimalFormat();
            decimalFormat.setMaximumFractionDigits(2);
            String amount = "$" + decimalFormat.format(amountsByCriteria.get(criteriaID));
            res.setMonto(amount);
            auxResult.put(criteriaID, res);
        }
        
        for(Map.Entry<String, com.vw.model.ResultSet> entry : auxResult.entrySet()) {
            result.add(entry.getValue());
        }

        Collections.sort(result);

        return result;
    }

    private List<DetailedResultSet> getDetailedReportFromCriteriaAdjust(String criteriaID,
            String initialDate, String finalDate, String solicitante) {
        List<DetailedResultSet> result = new ArrayList<DetailedResultSet>();
        //Here is a list with all the intelligent criteria
        LinkedHashMap<String, String> intelligentCriteria = getIntelligentCriteria();

        //Here is a map which contains all the claims from claim file and the respective criteria ID
        LinkedHashMap<String, String> originalClaims = getAllClaimsFromClaimCriteria(initialDate, finalDate);

        //The new data is gonna be saved here
        LinkedHashMap<String, String> filteredClaims = new LinkedHashMap();

        //Now we're gonna filters the claims affeected by a regular criteria
        for (Map.Entry<String, String> entry : originalClaims.entrySet()) {
            String criteriaIDFromOriginalClaim = entry.getValue();
            if (intelligentCriteria.containsKey(criteriaIDFromOriginalClaim)) {
                String claimIDFromOriginalClaim = entry.getKey();
                filteredClaims.put(claimIDFromOriginalClaim, criteriaIDFromOriginalClaim);
            } else if (intelligentCriteria.containsValue(criteriaIDFromOriginalClaim)) {
                String claimIDFromOriginalClaim = entry.getKey();
                filteredClaims.put(claimIDFromOriginalClaim, criteriaIDFromOriginalClaim);
            }
        }

        //At this point, we have all the claims from the claim criteria that were affected by an intelligent criteria
        //Now, we're gonna get all tha claims from the roc 
        HashMap<String, String> rocClaims = getRocClaims(initialDate, finalDate);

        //Now, we're gonna get all the claims from the data warehouse
        HashMap<String, String> dwhClaims = getDwhClaims(initialDate, finalDate);

        /*Now we're gonna compare the filterecClaims from claim criteria to 
         the claims from roc and datawarehouse*/
        for (Map.Entry<String, String> entry : filteredClaims.entrySet()) {
            String claimIDFromFilteredClaims = entry.getKey();
            String criteriaIDFromFilteredClaims = entry.getValue();
            if (rocClaims.containsKey(claimIDFromFilteredClaims)) {
                if (criteriaID.equals(criteriaIDFromFilteredClaims)) {
                    DetailedResultSet resultSet = getDetailedResultSetFromClaimCriteria(claimIDFromFilteredClaims,
                            solicitante, criteriaIDFromFilteredClaims, "roc");
                    result.add(resultSet);
                }

            } else if (dwhClaims.containsKey(claimIDFromFilteredClaims)) {
                if (criteriaID.equals(criteriaIDFromFilteredClaims)) {
                    DetailedResultSet resultSet = getDetailedResultSetFromClaimCriteria(claimIDFromFilteredClaims,
                            solicitante, criteriaIDFromFilteredClaims, "dwh");
                    result.add(resultSet);
                }
            }
        }
        return result;
    }

    private List<DetailedResultSet> getDetailedReportFromAnalistAdjust(String criteriaID,
            String initialDate, String finalDate, String solicitante) {
        List<DetailedResultSet> result = new ArrayList<DetailedResultSet>();
        //Here is a list with all the intelligent criteria
        LinkedHashMap<String, String> regularCriteria = getRegularCriteria();

        //Here is a map which contains all the claims from claim file and the respective criteria ID
        LinkedHashMap<String, String> originalClaims = getAllClaimsFromRoc(initialDate, finalDate);

        //The new data is gonna be saved here
        LinkedHashMap<String, String> filteredClaims = new LinkedHashMap();

        //Now we're gonna filters the claims affeected by a regular criteria
        for (Map.Entry<String, String> entry : originalClaims.entrySet()) {
            String criteriaIDFromOriginalClaim = entry.getValue();
            String[] ids = criteriaIDFromOriginalClaim.split("[*]+");
            for (int i = 0; i < ids.length; i++) {
                if (regularCriteria.containsKey(ids[i])) {
                    String claimIDFromOriginalClaim = entry.getKey();
                    filteredClaims.put(claimIDFromOriginalClaim, ids[i]);
                } else if (regularCriteria.containsValue(ids[i])) {
                    String claimIDFromOriginalClaim = entry.getKey();
                    filteredClaims.put(claimIDFromOriginalClaim, ids[i]);
                }
            }
        }

        //Now, we're gonna get all the claims from the data warehouse
        HashMap<String, String> dwhClaims = getDwhClaims(initialDate, finalDate);

        /*Now we're gonna compare the filterecClaims from claim criteria to 
         the claims from roc and datawarehouse*/
        for (Map.Entry<String, String> entry : filteredClaims.entrySet()) {
            String claimIDFromFilteredClaims = entry.getKey();
            String criteriaIDFromFilteredClaims = entry.getValue();
            if (dwhClaims.containsKey(claimIDFromFilteredClaims)) {
                if (criteriaID.equals(criteriaIDFromFilteredClaims)) {
                    DetailedResultSet resultSet =
                            getDetailedResultSetFromRoc(claimIDFromFilteredClaims,
                            solicitante, criteriaIDFromFilteredClaims, "");
                    result.add(resultSet);
                }
            }
        }
        return result;
    }

    private com.vw.model.ResultSet getResultSetFromCanceledClaimCriteria(boolean isIntelligentCriteria,
            String criteriaID, LinkedHashMap<String, String> filteredClaims) {
        String criteriaType = (isIntelligentCriteria) ? "Inteligente" : getCriteriaType(criteriaID);
        String hitNumber = getCriteriaHits(criteriaID);
        String amount = "0.0";
        String applicant = getCriteriaApplicant(criteriaID);
        String brand = getCriteriaBrand(criteriaID).toUpperCase();
        String daysSinceActivation = getCriteriaDaysSinceActivation(criteriaID);
        return new com.vw.model.ResultSet(criteriaID, criteriaType, hitNumber,
                amount, applicant, brand, daysSinceActivation);
    }

    private com.vw.model.ResultSet getResultSetFromCanceledRoc(boolean isIntelligentCriteria,
            String criteriaID, LinkedHashMap<String, String> filteredClaims,
            String claimID) {
        String criteriaType = (isIntelligentCriteria) ? "Inteligente" : getCriteriaType(criteriaID);
        String hitNumber = getCriteriaHits(criteriaID);
        String amount = "0.0";
        String applicant = getCriteriaApplicant(criteriaID);
        String brand = getCriteriaBrand(criteriaID).toUpperCase();
        String daysSinceActivation = getCriteriaDaysSinceActivation(criteriaID);
        return new com.vw.model.ResultSet(criteriaID, criteriaType, hitNumber,
                amount, applicant, brand, daysSinceActivation);
    }

    /**
     * This method will get the necessay data to fill a result set comparing
     * data between the claim criteria and the data warehouse It's used by the
     * getAdjustByAnalist/getAdjustByCriteria methods
     *
     * @param isIntelligentCriteria
     * @param criteriaID
     * @param filteredClaims
     * @param dwhClaims
     * @return a com.vw.model.ResultSet
     */
    private com.vw.model.ResultSet getResultSetFromDataWarehouse(boolean isIntelligentCriteria,
            String criteriaID, LinkedHashMap<String, String> filteredClaims,
            HashMap<String, String> dwhClaims) {
        String criteriaType = (isIntelligentCriteria) ? "Inteligente" : getCriteriaType(criteriaID);
        String hitNumber = getCriteriaHits(criteriaID);
        String amount = "0.0";
        String applicant = getCriteriaApplicant(criteriaID);
        String brand = getCriteriaBrand(criteriaID).toUpperCase();
        String daysSinceActivation = getCriteriaDaysSinceActivation(criteriaID);
        return new com.vw.model.ResultSet(criteriaID, criteriaType, hitNumber,
                amount, applicant, brand, daysSinceActivation);
    }

    /**
     * This method will get the necessary data to fill a result comparing data
     * between the claim criteria and the roc It's used by the
     * getAdjustByAnalist/getAdjustByCriteria methods
     *
     * @param isIntelligentCriteria
     * @param criteriaID
     * @param filteredClaims
     * @param rocClaims
     * @return
     */
    private com.vw.model.ResultSet getResultSetFromRoc(boolean isIntelligentCriteria,
            String criteriaID,
            LinkedHashMap<String, String> filteredClaims, HashMap<String, String> rocClaims) {
        String criteriaType = (isIntelligentCriteria) ? "Inteligente" : getCriteriaType(criteriaID);
        String hitNumber = getCriteriaHits(criteriaID);
        String amount = "0.0";
        String applicant = getCriteriaApplicant(criteriaID);
        String brand = getCriteriaBrand(criteriaID).toUpperCase();
        String daysSinceActivation = getCriteriaDaysSinceActivation(criteriaID);
        return new com.vw.model.ResultSet(criteriaID, criteriaType, hitNumber,
                amount, applicant, brand, daysSinceActivation);
    }

    /**
     * This method will get the days_since_activation value for an especific
     * criteria ID from the monthly roc It's used in the
     * getResultSetFromRoc/getResultSetFromDataDWarehouse methods
     *
     * @param criteriaID
     * @return
     */
    private String getCriteriaDaysSinceActivation(String criteriaID) {
        Calendar calendar = Calendar.getInstance();
        criteriaID += calendar.get(Calendar.MONTH) + 1;
        criteriaID += calendar.get(Calendar.YEAR);
        String daysSinceActivation = "0";
        String queryContent = "SELECT [roc_mensual_dias_activacion]\n"
                + "FROM [criterios_logicos].[dbo].[roc_mensual]\n"
                + "WHERE [roc_mensual_ID] = '" + criteriaID + "'";
        ResultSet resultSet = resultSetFromQuery(queryContent);
        try {
            while (resultSet.next()) {
                daysSinceActivation = Integer.toString(resultSet.getInt("roc_mensual_dias_activacion"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return (daysSinceActivation.isEmpty() || daysSinceActivation == null)
                ? "0" : daysSinceActivation;
    }

    /**
     * This method will return the brand of an especific criteria from the
     * criteria table It's used in the
     * getResultSetFromRoc/getResultSetFromDataDWarehouse methods
     *
     * @param criteriaID
     * @return
     */
    private String getCriteriaBrand(String criteriaID) {
        String brand = "";
        String queryContent = "SELECT [criterio_marca]\n"
                + "FROM [criterios_logicos].[dbo].[criterio]\n"
                + "WHERE [criterio_id_nuevo] = '" + criteriaID + "'\n"
                + "OR [criterio_id_viejo] = '" + criteriaID + "'";
        ResultSet resultSet = resultSetFromQuery(queryContent);
        try {
            while (resultSet.next()) {
                brand = resultSet.getString("criterio_marca");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return brand;
    }

    /**
     * This method will return the applicant from a specific criteria from the
     * criterio table It's used in the
     * getResultSetFromRoc/getResultSetFromDataDWarehouse methods
     *
     *
     * @param criteriaID
     * @return
     */
    private String getCriteriaApplicant(String criteriaID) {
        String applicant = "";
        String queryContent = "SELECT [criterio_solicitante]\n"
                + "FROM [criterios_logicos].[dbo].[criterio]\n"
                + "WHERE [criterio_id_nuevo] = '" + criteriaID + "'\n"
                + "OR [criterio_id_viejo] = '" + criteriaID + "'";
        ResultSet resultSet = resultSetFromQuery(queryContent);
        try {
            while (resultSet.next()) {
                applicant = resultSet.getString("criterio_solicitante");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return applicant;
    }

    /**
     * This method will get the amount of money that a specific criteria
     * affected. This amount it's the difference between the claim criteria
     * claim value and the data warehouse claim value It's used in the
     * getResultSetFromDataDWarehouse methods
     *
     * @param criteriaID
     * @param filteredClaims
     * @param dwhClaims
     * @return
     */
    private String getClaimAmountFromDWH(String criteriaID,
            LinkedHashMap<String, String> filteredClaims,
            HashMap<String, String> dwhClaims) {
        String amount = "";
        double amountD = 0.0;
        for (Map.Entry<String, String> entry : filteredClaims.entrySet()) {
            String criteriaIdFromEntry = entry.getValue();
            if (criteriaIdFromEntry.equals(criteriaID)) {
                String claimID = entry.getKey();
                if (dwhClaims.containsKey(claimID)) {
                    double amountFromRoc = getAmountFromRoc(claimID);
                    double amountFromDWH = getAmountFromDWH(claimID);
                    amountD += (amountFromRoc - amountFromDWH);
                }
            }
        }
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);
        amount = "$" + decimalFormat.format(amountD);
        return amount;
    }

    /**
     * This method will get the amount of money that a specific criteria
     * affected. This amount it's the difference between the claim criteria
     * claim value and the roc claim value It's used in the
     * getResultSetFromRocmethods
     *
     * @param criteriaID
     * @param filteredClaims
     * @param rocClaims
     * @return
     */
    private String getClaimAmountFromRoc(String criteriaID,
            LinkedHashMap<String, String> filteredClaims,
            HashMap<String, String> rocClaims) {
        String amount = "";
        double amountD = 0.0;
        for (Map.Entry<String, String> entry : filteredClaims.entrySet()) {
            String criteriaIdFromEntry = entry.getValue();
            if (criteriaID.equals(criteriaIdFromEntry)) {
                String claimID = entry.getKey();
                if (rocClaims.containsKey(claimID)) {
                    double amountFromClaimCriteria = getAmountFromClaimCriteria(claimID);
                    double amountFromRoc = getAmountFromRoc(claimID);
                    amountD += (amountFromClaimCriteria - amountFromRoc);
                }
            }
        }
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);
        amount = "$" + decimalFormat.format(amountD);
        return amount;
    }

    /**
     * This method will get the amount of money that a specific criteria
     * affected This amount came directly from the claim criteria It's used in
     * the getResultSetFromClaimCriteria
     *
     * @param criteriaID
     * @param filteredClaims
     * @return
     */
    private String getClaimAmountFromClaimCriteria(String criteriaID,
            LinkedHashMap<String, String> filteredClaims) {
        String amount = "0.0";
        double amountD = 0.0;
        for (Map.Entry<String, String> entry : filteredClaims.entrySet()) {
            String criteriaIdFromEntry = entry.getValue();
            if (criteriaID.equals(criteriaIdFromEntry)) {
                String claimID = entry.getKey();
                double amountFromClaimCriteria = getAmountFromClaimCriteria(claimID);
                amountD += amountFromClaimCriteria;
            }
        }
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);
        amount = "$" + decimalFormat.format(amountD);
        return amount;
    }

    /**
     *
     * @param criteriaID
     * @param filteredClaims
     * @return
     */
    private String getClaimAmountFromRoc(String criteriaID,
            LinkedHashMap<String, String> filteredClaims,
            String idClaim) {
        String amount = "0.0";
        double amountD = 0.0;
        for (Map.Entry<String, String> entry : filteredClaims.entrySet()) {
            String criteriaIdFromEntry = entry.getValue();
            String claimID = entry.getKey();
            if (criteriaID.equals(criteriaIdFromEntry) && claimID.equals(idClaim)) {
                double amountFromRoc = getAmountFromRoc(claimID);
                amountD += amountFromRoc;
            }
        }
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);
        amount = "$" + decimalFormat.format(amountD);
        return amount;
    }

    /**
     * This method will get the value of a specific claim in the data warehouse
     * It's used in the getCriteriaAmountFromDWH method.
     *
     * @param claimID
     * @return
     */
    private double getAmountFromDWH(String claimID) {
        String queryContent = "SELECT [dwh_total_sin_profit]\n"
                + "FROM [criterios_logicos].[dbo].[dwh]\n"
                + "WHERE [dwh_ID] = '" + claimID + "'";
        ResultSet resultSet = resultSetFromQuery(queryContent);
        double amount = 0.0;
        try {
            while (resultSet.next()) {
                amount = resultSet.getDouble("dwh_total_sin_profit");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return amount;
    }

    /**
     * This method will get the value of a specific claim in the roc It's used
     * in the getCriteriaAmountFromRoc method
     *
     * @param claimID
     * @return
     */
    private double getAmountFromRoc(String claimID) {
        String queryContent = "SELECT [roc_amount]\n"
                + "FROM [criterios_logicos].[dbo].[roc]\n"
                + "WHERE [roc_ID] = '" + claimID + "'";
        ResultSet resultSet = resultSetFromQuery(queryContent);
        double amount = 0.0;
        try {
            while (resultSet.next()) {
                amount = Double.parseDouble(resultSet.getString("roc_amount").replace(",", ""));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return amount;
    }

    /**
     * This method will get the value of a specific claim in the claim criteria
     * It's used in the getClaimAmountFromDWH/getClaimAmountFromRoc methods
     *
     * @param claimID
     * @return
     */
    private double getAmountFromClaimCriteria(String claimID) {
        String queryContent = "SELECT [claim_valor_total_imp]\n"
                + "FROM [criterios_logicos].[dbo].[claim]\n"
                + "WHERE [claim_ID] = '" + claimID + "'";
        ResultSet resultSet = resultSetFromQuery(queryContent);
        double amount = 0.0;
        try {
            while (resultSet.next()) {
                amount = resultSet.getDouble("claim_valor_total_imp");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return amount;
    }

    /**
     * This method will get the monthly amount of hits of an specific criteria
     * from the monthly roc It's used in the
     * getResultSetFromRoc/getResultSetFromDataDWarehouse methods
     *
     * @param criteriaID
     * @return
     */
    private String getCriteriaHits(String criteriaID) {
        String monthlyID = criteriaID;
        Calendar calendar = Calendar.getInstance();
        monthlyID += calendar.get(Calendar.MONTH) + 1;
        monthlyID += calendar.get(Calendar.YEAR);
        String queryContent = "SELECT [roc_mensual_numero_hits]\n"
                + "FROM [criterios_logicos].[dbo].[roc_mensual]\n"
                + "WHERE [roc_mensual_ID] = '" + monthlyID + "'";
        ResultSet resultSet = resultSetFromQuery(queryContent);
        String hits = "0";
        try {
            while (resultSet.next()) {
                int hitAmount = resultSet.getInt("roc_mensual_numero_hits");
                try {
                    hits = Integer.toString(hitAmount);
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return hits;
    }

    /**
     * This method will get the type of an specific criteria It's used in the
     * getResultSetFromRoc/getResultSetFromDataDWarehouse methods
     *
     * @param criteriaID
     * @return
     */
    private String getCriteriaType(String criteriaID) {
        String criteriaType = "";
        String queryContent = "SELECT [criterio_tipo]\n"
                + "FROM [criterios_logicos].[dbo].[criterio]\n"
                + "WHERE [criterio_id_viejo] = '" + criteriaID + "'\n"
                + "OR [criterio_id_nuevo] = '" + criteriaID + "'";
        ResultSet resultSet = resultSetFromQuery(queryContent);
        try {
            while (resultSet.next()) {
                criteriaType = resultSet.getString("criterio_tipo");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return (criteriaType == null)
                ? "No especificado" : criteriaType;
    }

    public String getDealerFromRoc(String idClaim) {
        String dealer = "Reclamación sin chasis";
        String query = "SELECT [roc_taller]\n"
                + "FROM [criterios_logicos].[dbo].[roc]\n"
                + "WHERE [roc_ID] = '" + idClaim + "'";
        ResultSet resultSet = resultSetFromQuery(query);
        try {
            while (resultSet.next()) {
                dealer = resultSet.getString("roc_taller");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return dealer;
    }

    public String getSerialFromRoc(String idClaim) {
        String serial = "Reclamación sin chasis";
        String query = "SELECT [roc_claim_serial]\n"
                + "FROM [criterios_logicos].[dbo].[roc]\n"
                + "WHERE [roc_ID] = '" + idClaim + "'";
        ResultSet resultSet = resultSetFromQuery(query);
        try {
            while (resultSet.next()) {
                serial = resultSet.getString("roc_claim_serial");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return serial;
    }

    public String getChasisFromRoc(String idClaim) {
        String chasis = "Reclamación sin chasis";
        String query = "SELECT [roc_chasis]\n"
                + "FROM [criterios_logicos].[dbo].[roc]\n"
                + "WHERE [roc_ID] = '" + idClaim + "'";
        ResultSet resultSet = resultSetFromQuery(query);
        try {
            while (resultSet.next()) {
                chasis = resultSet.getString("roc_chasis");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return chasis;
    }

    public String getClaimDealer(String idClaim) {
        String dealer = "Reclamación sin dealer";
        String query = "SELECT [claim_dealer]\n"
                + "FROM [criterios_logicos].[dbo].[claim]\n"
                + "WHERE [claim_ID] = '" + idClaim + "'";
        ResultSet resultSet = resultSetFromQuery(query);
        try {
            while (resultSet.next()) {
                dealer = resultSet.getString("claim_dealer");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return dealer;
    }

    public String getClaimSerial(String idClaim) {
        String serial = "Reclamación sin serial";
        String query = "SELECT [claim_serial]\n"
                + "FROM [criterios_logicos].[dbo].[claim]\n"
                + "WHERE [claim_ID] = '" + idClaim + "'";
        ResultSet resultSet = resultSetFromQuery(query);
        try {
            while (resultSet.next()) {
                serial = resultSet.getString("claim_serial");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return serial;
    }

    public String getClaimChasis(String idClaim) {
        String chasis = "Reclamación sin chasis";
        String query = "SELECT [claim_chasis]\n"
                + "FROM [criterios_logicos].[dbo].[claim]\n"
                + "WHERE [claim_ID] = '" + idClaim + "'";
        ResultSet resultSet = resultSetFromQuery(query);
        try {
            while (resultSet.next()) {
                chasis = resultSet.getString("claim_chasis");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return chasis;
    }

    public String getClaimAmountFromRoc(String idClaim) {
        double claimCriteriaAmount = 0.0;
        double rocAmount = 0.0;
        String query = "SELECT [claim_valor_total_imp]\n"
                + "FROM [criterios_logicos].[dbo].[claim]\n"
                + "WHERE [claim_ID] = '" + idClaim + "'";
        ResultSet resultSet = resultSetFromQuery(query);
        try {
            while (resultSet.next()) {
                claimCriteriaAmount = resultSet.getDouble("claim_valor_total_imp");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        query = "SELECT [roc_amount]\n"
                + "FROM [criterios_logicos].[dbo].[roc]\n"
                + "WHERE [roc_ID] = '" + idClaim + "'";
        resultSet = resultSetFromQuery(query);
        try {
            while (resultSet.next()) {
                rocAmount = Double.parseDouble(resultSet.getString("roc_amount").replace(",", ""));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        String amount;
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);
        amount = "$" + decimalFormat.format(claimCriteriaAmount - rocAmount);
        return amount;
    }

    public String getClaimAmountFromRocDWH(String idClaim) {
        double rocAmount = 0.0;
        double dwhAmount = 0.0;
        String query = "SELECT [roc_amount]\n"
                + "FROM [criterios_logicos].[dbo].[roc]\n"
                + "WHERE [roc_ID] = '" + idClaim + "'";
        ResultSet resultSet = resultSetFromQuery(query);
        try {
            while (resultSet.next()) {
                rocAmount = Double.parseDouble(resultSet.getString("roc_amount").replace(",", ""));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        query = "SELECT [dwh_total_sin_profit]\n"
                + "FROM [criterios_logicos].[dbo].[dwh]\n"
                + "WHERE [dwh_ID] = '" + idClaim + "'";
        resultSet = resultSetFromQuery(query);
        try {
            while (resultSet.next()) {
                dwhAmount = resultSet.getDouble("dwh_total_sin_profit");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        String amount;
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);
        amount = "$" + decimalFormat.format(rocAmount - dwhAmount);
        return amount;
    }

    public String getClaimAmountFromDWH(String idClaim) {
        double claimCriteriaAmount = 0.0;
        double dwhAmount = 0.0;
        String query = "SELECT [claim_valor_total_imp]\n"
                + "FROM [criterios_logicos].[dbo].[claim]\n"
                + "WHERE [claim_ID] = '" + idClaim + "'";
        ResultSet resultSet = resultSetFromQuery(query);
        try {
            while (resultSet.next()) {
                claimCriteriaAmount = resultSet.getDouble("claim_valor_total_imp");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        query = "SELECT [dwh_total_sin_profit]\n"
                + "FROM [criterios_logicos].[dbo].[dwh]\n"
                + "WHERE [dwh_ID] = '" + idClaim + "'";
        resultSet = resultSetFromQuery(query);
        try {
            while (resultSet.next()) {
                dwhAmount = resultSet.getDouble("dwh_total_sin_profit");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        String amount;
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);
        amount = "$" + decimalFormat.format(claimCriteriaAmount - dwhAmount);
        return amount;
    }

    /**
     * This method will get the ID's of all the claims from the data warehouse
     * It's used in the getResultSetFromDataDWarehouse methods
     *
     * @return
     */
    private HashMap<String, String> getDwhClaims(String initialDate, String finalDate) {
        HashMap<String, String> map = new HashMap();
        String queryContent = "SELECT [dwh_ID]\n"
                + "FROM [criterios_logicos].[dbo].[dwh]\n"
                + "WHERE [dwh_fecha_reclamacion] BETWEEN '"
                + initialDate + "' AND '" + finalDate + "'";
        ResultSet resultSet = resultSetFromQuery(queryContent);
        String dwhID = "";
        try {
            while (resultSet.next()) {
                dwhID = resultSet.getString("dwh_ID");
                if (!dwhID.equals("") || !dwhID.isEmpty() || dwhID != null) {
                    map.put(dwhID, "");
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return map;
    }

    /**
     * This method will return the ID's of all the roc claims It's used in the
     * getResultSetFromRoc method
     *
     * @return
     */
    private HashMap<String, String> getRocClaims(String initialDate, String finalDate) {
        HashMap<String, String> map = new HashMap();
        String queryContent = "SELECT [roc_ID]\n"
                + "FROM [criterios_logicos].[dbo].[roc]\n"
                + "WHERE [roc_claim_date] BETWEEN '"
                + initialDate + "' AND '" + finalDate + "'";
        ResultSet resultSet = resultSetFromQuery(queryContent);
        String rocClaimID = "";
        try {
            while (resultSet.next()) {
                rocClaimID = resultSet.getString("roc_ID");
                if (!rocClaimID.isEmpty() || rocClaimID != null || !rocClaimID.equals("")) {
                    map.put(rocClaimID, "");
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return map;
    }

    /**
     * This method will get the ID's of all the claims from the easy It's used
     * in the getCanceledClaimsByCriteria method
     *
     * @return
     */
    private HashMap<String, String> getEasyClaims(String initialDate, String finalDate) {
        HashMap<String, String> map = new HashMap();
        String queryConten = "SELECT [easy_ID]\n"
                + "FROM [criterios_logicos].[dbo].[easy]\n"
                + "WHERE [easy_claim_date] BETWEEN '"
                + initialDate + "' AND '" + finalDate + "'";
        ResultSet resultSet = resultSetFromQuery(queryConten);
        String easyClaimID = "";
        try {
            while (resultSet.next()) {
                easyClaimID = resultSet.getString("easy_ID");
                if (!easyClaimID.isEmpty() || easyClaimID != null || !easyClaimID.equals("")) {
                    map.put(easyClaimID, "");
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return map;
    }

    /**
     * This method will get the ID's of all the claims from the claim criteria
     * it's used in the getCanceledClaimsByAnalist method
     *
     * @return
     */
    private HashMap<String, String> getClaimCriteriaClaims(String initialDate, String finalDate) {
        HashMap<String, String> map = new HashMap();
        String queryContent = "SELECT [claim_ID]\n"
                + "FROM [criterios_logicos].[dbo].[claim]\n"
                + "WHERE [claim_claim_date] BETWEEN '"
                + initialDate + "' AND '" + finalDate + "'";
        ResultSet resultSet = resultSetFromQuery(queryContent);
        String claimCriteriaClimID = "";
        try {
            while (resultSet.next()) {
                claimCriteriaClimID = resultSet.getString("claim_ID");
                if (!claimCriteriaClimID.isEmpty() || claimCriteriaClimID != null
                        || !claimCriteriaClimID.equals("")) {
                    map.put(claimCriteriaClimID, "");
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return map;
    }

    /**
     * This method will get all the claims from the claim criteria It's used in
     * the getAdjustByAnalist/getAdjustByCriteria/getCanceledClaimsByCriteria
     * methods
     *
     * @return
     */
    private LinkedHashMap<String, String> getAllClaimsFromClaimCriteria(String initialDate,
            String finalDate) {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        String queryContent = "SELECT [claim_ID], [claim_criterio_id]\n"
                + "FROM [criterios_logicos].[dbo].[claim]\n"
                + "WHERE [claim_claim_date] BETWEEN '"
                + initialDate + "' AND '" + finalDate + "'\n"
                + "ORDER BY [claim_ID]";
        ResultSet resultSet = resultSetFromQuery(queryContent);
        try {
            while (resultSet.next()) {
                map.put(resultSet.getString("claim_ID"), resultSet.getString("claim_criterio_id"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return map;
    }

    /**
     * This method will get all the claims from the roc It's used in the
     * getCanceledByAnalist method
     *
     * @return
     */
    private LinkedHashMap<String, String> getAllClaimsFromRoc(String initialDate,
            String finalDate) {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        String queryContent = "SELECT [roc_ID], [roc_criterios]\n"
                + "FROM [criterios_logicos].[dbo].[roc]\n"
                + "WHERE [roc_claim_date] BETWEEN '"
                + initialDate + "' AND '" + finalDate + "'";
        ResultSet resultSet = resultSetFromQuery(queryContent);
        try {
            while (resultSet.next()) {
                map.put(resultSet.getString("roc_ID"), resultSet.getString("roc_criterios"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return map;
    }

    /**
     * This method will get all the intelligent criteria It's used in the
     * getAdjustByCriteria/getCanceledClaimsByCriteria method
     *
     * @return a List with the full id of every intelligent criteria
     */
    private LinkedHashMap<String, String> getIntelligentCriteria() {
        LinkedHashMap<String, String> criteria = new LinkedHashMap();
        String queryContent = "SELECT [criterio_id_nuevo], [criterio_id_viejo]\n"
                + "FROM [criterios_logicos].[dbo].[criterio]\n"
                + "WHERE [criterio_tipo] = 'inteligente'";
        ResultSet resultSet = resultSetFromQuery(queryContent);
        try {
            while (resultSet.next()) {
                criteria.put(resultSet.getString("criterio_id_nuevo"),
                        resultSet.getString("criterio_id_viejo"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return criteria;
    }

    /**
     * This method will return all the non-intelligent criteria It's used in the
     * getAdjustByAnalist method
     *
     * @return
     */
    private LinkedHashMap<String, String> getRegularCriteria() {
        LinkedHashMap<String, String> criteria = new LinkedHashMap();
        String queryContent = "SELECT [criterio_id_nuevo], [criterio_id_viejo]\n"
                + "FROM [criterios_logicos].[dbo].[criterio]\n"
                + "WHERE [criterio_tipo] != 'inteligente'";
        ResultSet resultSet = resultSetFromQuery(queryContent);
        try {
            while (resultSet.next()) {
                criteria.put(resultSet.getString("criterio_id_nuevo"),
                        resultSet.getString("criterio_id_viejo"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return criteria;
    }

    /**
     * This method will get all the non repeated claims from roc, data warehouse
     * and claim criteria and order them by brand It's used int the
     * reportTotalClaimsByBrand jsp/ReportTotalClaimsByBrandServlet
     *
     * @param initalDate
     * @param finalDate
     * @return a list of brands and it's respective number of claims (by month)
     */
    public List<ResultSetForTotalClaims> getTotalClaimsByBrand(String initalDate,
            String finalDate) {

        //This is gonna be the final result
        List<ResultSetForTotalClaims> results = new ArrayList();
        ResultSet resultSet;
        String queryContent;

        //This map will help to not count repeated claims
        HashMap<String, String> claims = new HashMap();

        //Here we get the criterias by brand
        LinkedHashMap<String, String> audiNewCriteria = getNewCriteriaByBrand("audi");
        LinkedHashMap<String, String> seatNewCriteria = getNewCriteriaByBrand("seat");
        LinkedHashMap<String, String> vwNewCriteria = getNewCriteriaByBrand("volkswagen");
        LinkedHashMap<String, String> multiNewCriteria = getNewCriteriaByBrand("multimarca");
        LinkedHashMap<String, String> nfzNewCriteria = getNewCriteriaByBrand("nfz");
        LinkedHashMap<String, String> audiOldCriteria = getOldCriteriaByBrand("audi");
        LinkedHashMap<String, String> seatOldCriteria = getOldCriteriaByBrand("seat");
        LinkedHashMap<String, String> vwOldCriteria = getOldCriteriaByBrand("volkswagen");
        LinkedHashMap<String, String> multiOldCriteria = getOldCriteriaByBrand("multimarca");
        LinkedHashMap<String, String> nfzOldCriteria = getOldCriteriaByBrand("nfz");

        //counters for each brand;
        int audiCount = 0;
        int seatCount = 0;
        int vwCount = 0;
        int multiCount = 0;
        int nfzCount = 0;

        //This part will get the data warehouse claims
        queryContent = "SELECT [dwh_id], "
                + "[dwh_marca]\n"
                + "FROM [criterios_logicos].[dbo].[dwh]\n"
                + "WHERE [dwh_fecha_reclamacion] BETWEEN '"
                + initalDate + "' AND '" + finalDate + "'";
        resultSet = resultSetFromQuery(queryContent);
        try {
            while (resultSet.next()) {
                String id = resultSet.getString("dwh_id");
                String marca = resultSet.getString("dwh_marca").toLowerCase();
                if (!claims.containsKey(id)) {
                    claims.put(id, id);
                    if (marca.equals("seat")) {
                        seatCount++;
                    } else if (marca.equals("audi")) {
                        audiCount++;
                    } else if (marca.equals("vw pkw")) {
                        vwCount++;
                    } else if (marca.equals("nfz")) {
                        nfzCount++;
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        //This part is for get the claims from roc
        queryContent = "SELECT [roc_ID],"
                + "[roc_criterios]\n"
                + "FROM [criterios_logicos].[dbo].[roc]\n"
                + "WHERE [roc_claim_date] BETWEEN '"
                + initalDate + "' AND '" + finalDate + "'";
        resultSet = resultSetFromQuery(queryContent);
        try {
            while (resultSet.next()) {
                String[] ids = resultSet.getString("roc_criterios").split("[*]+");
                String id = resultSet.getString("roc_ID").trim();
                if (!claims.containsKey(id)) {
                    claims.put(id, id);
                    for (String id1 : ids) {
                        if (audiNewCriteria.containsKey(id1) || audiOldCriteria.containsKey(id1)) {
                            audiCount++;
                            continue;
                        }
                        if (seatNewCriteria.containsKey(id1) || seatOldCriteria.containsKey(id1)) {
                            seatCount++;
                            continue;
                        }
                        if (vwNewCriteria.containsKey(id1) || vwOldCriteria.containsKey(id1)) {
                            vwCount++;
                            continue;
                        }
                        if (multiNewCriteria.containsKey(id1) || multiOldCriteria.containsKey(id1)) {
                            multiCount++;
                            continue;
                        }
                        if (nfzNewCriteria.containsKey(id1) || nfzOldCriteria.containsKey(id1)) {
                            nfzCount++;
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        //This part will get the data from claim claims
        queryContent = "SELECT [claim_ID]\n"
                + "      ,[claim_criterio_id]\n"
                + "FROM [criterios_logicos].[dbo].[claim]\n"
                + "WHERE [claim_claim_date] BETWEEN '"
                + initalDate + "' AND '" + finalDate + "'";
        resultSet = resultSetFromQuery(queryContent);
        try {
            while (resultSet.next()) {
                String id = resultSet.getString("claim_ID");
                String criterioID = resultSet.getString("claim_criterio_id");
                if (!claims.containsKey(id)) {
                    claims.put(id, id);
                    if (audiNewCriteria.containsKey(criterioID) || audiOldCriteria.containsKey(criterioID)) {
                        audiCount++;
                        continue;
                    }
                    if (seatNewCriteria.containsKey(criterioID) || seatOldCriteria.containsKey(criterioID)) {
                        seatCount++;
                        continue;
                    }
                    if (vwNewCriteria.containsKey(criterioID) || vwOldCriteria.containsKey(criterioID)) {
                        vwCount++;
                        continue;
                    }
                    if (multiNewCriteria.containsKey(criterioID) || multiOldCriteria.containsKey(criterioID)) {
                        multiCount++;
                        continue;
                    }
                    if (nfzNewCriteria.containsKey(criterioID) || nfzOldCriteria.containsKey(criterioID)) {
                        nfzCount++;
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        results.add(new ResultSetForTotalClaims(Integer.toString(audiCount), "Audi"));
        results.add(new ResultSetForTotalClaims(Integer.toString(seatCount), "Seat"));
        results.add(new ResultSetForTotalClaims(Integer.toString(vwCount), "Volkswagen"));
        results.add(new ResultSetForTotalClaims(Integer.toString(nfzCount), "NTFZ"));
        results.add(new ResultSetForTotalClaims(Integer.toString(multiCount), "Multimarca"));
        return results;
    }

    /**
     * this method will get all the criteria (by it's new id) for a specific
     * brand It's used int the getTotalClaimsByBrand method
     *
     * @param brand
     * @return
     */
    private LinkedHashMap<String, String> getNewCriteriaByBrand(String brand) {
        LinkedHashMap<String, String> criteria = new LinkedHashMap();
        String queryContent = "SELECT [criterio_id_nuevo]\n"
                + "FROM [criterios_logicos].[dbo].[criterio]\n"
                + "WHERE [criterio_marca] = '" + brand + "'";
        ResultSet resultSet = resultSetFromQuery(queryContent);
        try {
            while (resultSet.next()) {
                criteria.put(resultSet.getString("criterio_id_nuevo"), brand);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return criteria;
    }

    /**
     * this method will get all the criteria (by it's old id) for a specific
     * brand It's used in the getTotalClaimsByBrand method
     *
     * @param brand
     * @return
     */
    private LinkedHashMap<String, String> getOldCriteriaByBrand(String brand) {
        LinkedHashMap<String, String> criteria = new LinkedHashMap();
        String queryContent = "SELECT [criterio_id_viejo]\n"
                + "FROM [criterios_logicos].[dbo].[criterio]\n"
                + "WHERE [criterio_marca] = '" + brand + "'";
        ResultSet resultSet = resultSetFromQuery(queryContent);
        try {
            while (resultSet.next()) {
                criteria.put(resultSet.getString("criterio_id_viejo"), brand);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return criteria;
    }

    //STANDARD DATA-BASE OPS METHODS
    /**
     * this method is used for the SELECT querys
     *
     * @param query
     * @return a resultser with the result of the query
     */
    public ResultSet resultSetFromQuery(String query) {
        try {
            statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    /**
     * this method is used for the DELETE, UPDATE and INSERT querys
     *
     * @param query
     * @return true if the query was succesfull, false otherwise
     */
    public boolean executeQuery(String query, boolean updatable) {
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.commit();
            statement.close();
            return true;
        } catch (SQLException ex) {
            if (!ex.getMessage().contains("Infracción de la restricción PRIMARY KEY")) {
                System.out.println(ex.getMessage() + " " + query);
            } else {
                System.out.println("El registro ya existe...");
                if (updatable) {
                    query = query.toLowerCase();
                    String values = query.split("values")[1];
                    String[] vals = values.split("[,]+");
                    String rocID = vals[0].replace("(", "");
                    String numeroHits = vals[2];
                    String diasActivacion = vals[3];
                    String queryContent = "UPDATE [criterios_logicos].[dbo].[roc_mensual]\n"
                            + "SET [roc_mensual_numero_hits] = " + numeroHits + "\n"
                            + "    ,[roc_mensual_dias_activacion] = " + diasActivacion + "\n"
                            + "WHERE roc_mensual_ID = '" + rocID + "'";
                    executeQuery(queryContent, false);
                }
            }
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex1) {
                    System.out.println(ex1);
                }
            }
        }
        return false;
    }
    
    public boolean setBussinessApprovedCriteria(String id) {
        String queryContent = "use criterios_logicos\n"
                + "update criterios_logicos.dbo.criterio\n"
                + "set criterio_aprobado_negocio = 's'\n"
                + "where criterio_ID = '" + id + "'";
        System.out.println(queryContent);
        return executeQuery(queryContent, false);
    }

    public boolean setAdminApprovedCriteria(String id) {
        String queryContent = "use criterios_logicos\n"
                + "update criterios_logicos.dbo.criterio\n"
                + "set criterio_aprobado_admin = 's'\n"
                + "where criterio_ID = '" + id + "'";
        return executeQuery(queryContent, false);
    }
    
    public List<String> getNotAdminApprovedCriteria() {
        String queryContent = "USE criterios_logicos\n"
                + "SELECT criterio_ID\n"
                + "FROM criterios_logicos.dbo.criterio\n"
                + "WHERE criterio_aprobado_admin = 'n'\n"
                + "AND criterio_aprobado_negocio = 's'";
        ResultSet resultSet = resultSetFromQuery(queryContent);
        List<String> criterios = new ArrayList<String>();
        try {
            while (resultSet.next()) {
                criterios.add(resultSet.getString(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return criterios;
    }

    public List<String> getNotBussinessApprovedCriteria() {
        String queryContent = "USE criterios_logicos\n"
                + "SELECT criterio_ID\n"
                + "FROM criterios_logicos.dbo.criterio\n"
                + "WHERE criterio_aprobado_negocio = 'n'";
        ResultSet resultSet = resultSetFromQuery(queryContent);
        List<String> criterios = new ArrayList<String>();
        try {
            while (resultSet.next()) {
                criterios.add(resultSet.getString(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return criterios;
    }
    
    public boolean deleteCriteriaByID(String id) {
        String queryContent = "use criterios_logicos\n"
                + "delete from criterios_logicos.dbo.criterio\n"
                + "where criterio_ID = '" + id + "'";
        return executeQuery(queryContent, false);
    }
    

    /**
     * this method closes the connection to the data base
     *
     * @return true if the connection was closed, false otherwise
     */
    public boolean closeConnection() {
        try {
            connection.close();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
}
