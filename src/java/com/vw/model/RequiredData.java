/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vw.model;

import java.util.LinkedHashMap;

/**
 * This class will provide the necessary data for each file/table and the specific data type
 * @author Adrián Ochoa Martínez
 */
public class RequiredData {
    
    private LinkedHashMap<String, String> roc;
    private LinkedHashMap<String, String> easy;
    private LinkedHashMap<String, String> claim;
    private LinkedHashMap<String, String> data;
    private LinkedHashMap<String, String> monthlyRoc;
    private LinkedHashMap<String, String> rocTypes;
    private LinkedHashMap<String, String> easyTypes;
    private LinkedHashMap<String, String> claimTypes;
    private LinkedHashMap<String, String> dataTypes;
    private LinkedHashMap<String, String> monthlyRocTypes;
    
    public RequiredData () {
        initRoc();
        initEasy();
        initClaim();
        initData();
        initMonthlyRoc();
        initRocTypes();
        initEasyTypes();
        initClaimTypes();
        initDataTypes();
        initMonthlyTypes();
    }
    
    private void initRocTypes() {
        rocTypes = new LinkedHashMap<String, String>();
        rocTypes.put("roc_id", "string");
        rocTypes.put("roc_chasis", "string");
        rocTypes.put("roc_numero_reclamacion", "string");
        rocTypes.put("roc_claim_serial", "string");
        rocTypes.put("roc_importador", "string");
        rocTypes.put("roc_pais_iso", "string");
        rocTypes.put("roc_fabricante", "string");
        rocTypes.put("roc_taller", "string");
        rocTypes.put("roc_claim_date", "date");//00/00/0000
        rocTypes.put("roc_n_at", "string");
        rocTypes.put("roc_claim_type_imp", "string");
        rocTypes.put("roc_claim_type_mft", "string");
        rocTypes.put("roc_status", "int");
        rocTypes.put("roc_criterios", "string");
        rocTypes.put("roc_criterios_version", "string");
        rocTypes.put("roc_amount", "string");
        rocTypes.put("roc_imp_currency", "string");
    }
    
    private void initEasyTypes() {
        easyTypes = new LinkedHashMap<String, String> ();
        easyTypes.put("easy_ID", "string");
        easyTypes.put("easy_chasis", "string");
        easyTypes.put("easy_numero_reclamacion", "string");
        easyTypes.put("easy_claim_serial", "string");
        easyTypes.put("easy_importador", "string");
        easyTypes.put("easy_pais_iso", "string");
        easyTypes.put("easy_fabricante", "string");
        easyTypes.put("easy_taller", "string");
        easyTypes.put("easy_claim_date", "date");
        easyTypes.put("easy_n_at", "string");
        easyTypes.put("easy_claim_type_imp", "string");
        easyTypes.put("easy_claim_type_mft", "string");
        easyTypes.put("easy_age_in_level", "int");
        easyTypes.put("easy_age_overall", "int");
        easyTypes.put("easy_level", "string");
        easyTypes.put("easy_status", "string");
        easyTypes.put("easy_required_action", "string");
        easyTypes.put("easy_dealer_status", "string");
        easyTypes.put("easy_imp_currency", "string");
        easyTypes.put("easy_value_imp_currency", "string");
    }
    
    private void initClaimTypes () {
        claimTypes = new LinkedHashMap<String, String> ();
        claimTypes.put("claim_ID", "string");
        claimTypes.put("claim_chasis", "string");
        claimTypes.put("claim_numero_reclamacion", "string");
        claimTypes.put("claim_serial", "string");
        claimTypes.put("claim_manufacturer", "string");
        claimTypes.put("claim_iso_land", "string");
        claimTypes.put("claim_importer", "string");
        claimTypes.put("claim_dealer", "string");
        claimTypes.put("claim_claim_date", "string");
        claimTypes.put("claim_service_id", "string");
        claimTypes.put("claim_current_status", "string");
        claimTypes.put("claim_fecha_reparacion", "string");
        claimTypes.put("claim_criterio_id", "string");
        claimTypes.put("claim_valor_mano_obra_imp", "double");
        claimTypes.put("claim_valor_material_nivel_imp", "double");
        claimTypes.put("claim_valor_total_imp", "double");
        claimTypes.put("claim_ut_imp", "int");
        claimTypes.put("claim_importe_total_nivel_fabrica_importador_euros", "double");
        claimTypes.put("claim_mano_obra_fabrica_importador_euros", "double");
        claimTypes.put("claim_uts_fabrica_importador", "int");
        claimTypes.put("claim_total_material_nivel_fabricante_euros", "double");
        claimTypes.put("claim_moneda_nivel_importador", "string");
        claimTypes.put("claim_moneda_nivel_fabricante", "string");
    }
    
    private void initDataTypes () {
        dataTypes = new LinkedHashMap<String, String> ();
        dataTypes.put("dwh_ID", "string");
        dataTypes.put("dwh_chasis", "string");
        dataTypes.put("dwh_numero_reclamacion", "string");
        dataTypes.put("dwh_claim_serial", "string");
        dataTypes.put("dwh_marca", "string");
        dataTypes.put("dwh_dealer", "string");
        dataTypes.put("dwh_fecha_reclamacion", "date");
        dataTypes.put("dwh_fecha_pago", "date");
        dataTypes.put("dwh_platform", "string");
        dataTypes.put("dwh_n_at", "string");
        dataTypes.put("dwh_damage_code", "string");
        dataTypes.put("dwh_descripcion", "string");
        dataTypes.put("dwh_spare_part_description", "string");
        dataTypes.put("dwh_claim_adjuster", "string");
        dataTypes.put("dwh_uts", "int");
        dataTypes.put("dwh_mo_int", "double");
        dataTypes.put("dwh_mat_int_sin_profit", "double");
        dataTypes.put("dwh_mo_ext", "double");
        dataTypes.put("dwh_mat_ext_sin_profit", "double");
        dataTypes.put("dwh_total_sin_profit", "double");
        dataTypes.put("dwh_total_pagado", "double");
    }
    
    private void initMonthlyTypes () {
        monthlyRocTypes = new LinkedHashMap<String, String>();
        monthlyRocTypes.put("roc_mensual_ID", "string");
        monthlyRocTypes.put("roc_mensual_criterio_ID", "string");
        monthlyRocTypes.put("roc_mensual_numero_hits", "int");
        monthlyRocTypes.put("roc_mensual_dias_activacion", "int");
        monthlyRocTypes.put("roc_mensual_fecha", "string");
    }
    
    private void initRoc() {
        roc = new LinkedHashMap<String, String>();
        roc.put("roc_id", "ID");
        roc.put("roc_chasis", "bastidor");
        roc.put("roc_numero_reclamacion", "numerodereclamacion");
        roc.put("roc_claim_serial", "claimserialno");
        roc.put("roc_importador", "importador");
        roc.put("roc_pais_iso", "paisiso");
        roc.put("roc_fabricante", "fabricante");
        roc.put("roc_taller", "taller");
        roc.put("roc_claim_date", "claimdate");//00/00/0000
        roc.put("roc_n_at", "nat");
        roc.put("roc_claim_type_imp", "1digitclaimtypeimp*2+3digitclaimtypeimp");
        roc.put("roc_claim_type_mft", "1digitclaimtypemft*2+3digitclaimtypemft");
        roc.put("roc_status", "status");
        roc.put("roc_criterios", "identificador01*identificador02*identificador03"
                + "*identificador04*identificador05*identificador06*identificador07"
                + "*identificador08*identificador09*identificador10");
        roc.put("roc_criterios_version", "criterionversion01*criterionversion02"
                + "*criterionversion03*criterionversion04*criterionversion05"
                + "*criterionversion06*criterionversion07*criterionversion08"
                + "*criterionversion09*criterionversion10");
        roc.put("roc_amount", "amountcalculculatedwf1impcurrency");
        roc.put("roc_imp_currency", "wf1impcurrency");
    }
    
    private void initEasy () {
        easy = new LinkedHashMap<String, String> ();
        easy.put("easy_ID", "id");
        easy.put("easy_chasis", "bastidor");
        easy.put("easy_numero_reclamacion", "númerodereclamación");
        easy.put("easy_claim_serial", "claimserialno");
        easy.put("easy_importador", "importador");
        easy.put("easy_pais_iso", "paisiso");
        easy.put("easy_fabricante", "fabricante");
        easy.put("easy_taller", "taller");
        easy.put("easy_claim_date", "claimdate");
        easy.put("easy_n_at", "nat");
        easy.put("easy_claim_type_imp", "1digitclaimtypeimp*2+3digitclaimtypeimp");
        easy.put("easy_claim_type_mft", "1digitclaimtypemft*2+3digitclaimtypemft");
        easy.put("easy_age_in_level", "age[days]inlevel");
        easy.put("easy_age_overall", "age[days]overall");
        easy.put("easy_level", "level");
        easy.put("easy_status", "status");
        easy.put("easy_required_action", "requiredaction");
        easy.put("easy_dealer_status", "dealerstatus(asof)");
        easy.put("easy_imp_currency", "impcurrency");
        easy.put("easy_value_imp_currency", "valueimpcurrency");
    }
    
    private void initClaim() {
        claim = new LinkedHashMap<String, String> ();
        claim.put("claim_ID", "id");
        claim.put("claim_chasis", "vin");
        claim.put("claim_numero_reclamacion", "claim_no");
        claim.put("claim_serial", "serial_no");
        claim.put("claim_manufacturer", "manufacturer");
        claim.put("claim_iso_land", "iso_land");
        claim.put("claim_importer", "importer");
        claim.put("claim_dealer", "dealer");
        claim.put("claim_claim_date", "claim_date");
        claim.put("claim_service_id", "service_id");
        claim.put("claim_current_status", "currentstatusofclaim");
        claim.put("claim_fecha_reparacion", "rep_annahme_dat");
        claim.put("claim_criterio_id", "krit_id");
        claim.put("claim_valor_mano_obra_imp", "p_ges_lohn_k_pw");
        claim.put("claim_valor_material_nivel_imp", "p_ges_mat_preis_k_pw");
        claim.put("claim_valor_total_imp", "p_antrwert_k_pw");
        claim.put("claim_ut_imp", "p_ges_zeiteinheiten_k");
        claim.put("claim_importe_total_nivel_fabrica_importador_euros", "i_antrwert_k_kw");
        claim.put("claim_mano_obra_fabrica_importador_euros", "i_ges_lohn_k_kw");
        claim.put("claim_uts_fabrica_importador", "i_ges_zeiteinheiten_k");
        claim.put("claim_total_material_nivel_fabricante_euros", "i_ges_mat_preis_k_kw");
        claim.put("claim_moneda_nivel_importador", "wwk_waehrungsschluessel_import");
        claim.put("claim_moneda_nivel_fabricante", "wwk_waehrungsschluessel_herst");
    }
    
    private void initData () {
        data = new LinkedHashMap<String, String> ();
        data.put("dwh_ID", "ID");
        data.put("dwh_chasis", "vehicleidnumber");
        data.put("dwh_numero_reclamacion", "repairorder");
        data.put("dwh_claim_serial", "claimserial");
        data.put("dwh_marca", "marca");
        data.put("dwh_dealer", "dealer");
        data.put("dwh_fecha_reclamacion", "captura");
        data.put("dwh_fecha_pago", "pago");
        data.put("dwh_platform", "platform");
        data.put("dwh_n_at", "at");
        data.put("dwh_damage_code", "damagecode");
        data.put("dwh_descripcion", "descripcion");
        data.put("dwh_spare_part_description", "sparepartdescriptionsp");
        data.put("dwh_claim_adjuster", "claimadjuster");
        data.put("dwh_uts", "ut's");
        data.put("dwh_mo_int", "moint");
        data.put("dwh_mat_int_sin_profit", "matintsinprofit");
        data.put("dwh_mo_ext", "moext");
        data.put("dwh_mat_ext_sin_profit", "matextsinprofit");
        data.put("dwh_total_sin_profit", "totalsinprofit");
        data.put("dwh_total_pagado", "totalpagadomxn");
    }
    
    private void initMonthlyRoc() {
        monthlyRoc = new LinkedHashMap<String, String>();
        monthlyRoc.put("roc_mensual_ID", "ID");
        monthlyRoc.put("roc_mensual_criterio_ID", "identificador");
        monthlyRoc.put("roc_mensual_numero_hits", "cantidadderesultadosestemes");
        monthlyRoc.put("roc_mensual_dias_activacion", "dayssinceactivation");
        monthlyRoc.put("roc_mensual_fecha", "");
    }
    
    public LinkedHashMap<String, String> getRoc () {
        return roc;
    }
    
    public LinkedHashMap<String, String> getEasy () {
        return easy;
    }
    
    public LinkedHashMap<String, String> getClaim () {
        return claim;
    }
    
    public LinkedHashMap<String, String> getData () {
        return data;
    }
    
    public LinkedHashMap<String, String> getMonthlyRoc() {
        return monthlyRoc;
    }
    
    public LinkedHashMap<String, String> getRocTypes () {
        return rocTypes;
    }
    
    public LinkedHashMap<String, String> getEasyTypes () {
        return easyTypes;
    }
    
    public LinkedHashMap<String, String> getDataTypes () {
        return dataTypes;
    }
    
    public LinkedHashMap<String, String> getClaimTypes () {
        return claimTypes;
    }
    
    public LinkedHashMap<String, String> getMonthlyTypes () {
        return monthlyRocTypes;
    }
    
}
