/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spoper_ufc_nesbel;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Belchior
 */
public class Albun {
    int albun_id;
    float pr_compra;
    String dt_compra, dt_gravacao, tipo_compra, descr;
    int tipo_compra_id, gravadora_id;
    public Cd[] cds = new Cd[4];
    
    public Albun(){
        
    }
    public void setAlbunid(int novoAlbun_id){
        albun_id = novoAlbun_id;
    }
    public void setAlbun_id(int novoAlbun_id){
        albun_id = novoAlbun_id;
    }
    public void setPr_compra(float novoPr_compra){
        pr_compra = novoPr_compra;
    }
    public void setDt_compra(String novaDt_compra){
        dt_compra = novaDt_compra;
    }
    public void setDt_gravacao(String novaDt_gravacao){
        dt_gravacao = novaDt_gravacao;
    }
    public void setTipo_compra_id(int novoTipo_compra_id){
        tipo_compra_id = novoTipo_compra_id;
    }
    public void setGravadora_id(int novaGravadora_id){
        gravadora_id = novaGravadora_id;
    }
    
    public Albun[] listaAlbuns(){
        Albun[] albuns = new Albun[100];
        String SQL = "";
        SQL += "SELECT " 
                + "a.albun_id, "
                + "a.dt_compra, "
                + "a.descr, "
                + "a.dt_gravacao, "
                + "t.descr as tipo_compra, "
                + "g.nome, "
                + "g.endereco, "
                + "g.website "
                + "FROM "
                + "albuns a, "
                + "tipos_compra t, "
                + "gravadoras g "
                + "WHERE "
                + "a.tipo_compra_id = t.tipo_id AND "
                + "a.gravadora_id = g.gravadora_id;";
                
                
        ResultSet rs = DbUtils.Lista(SQL);
        try{
            int count = 0;
            Cd[] cds;
            while (rs.next()) { 
                Albun albun = new Albun();
                albun.albun_id = Integer.parseInt(rs.getString("albun_id"));
                albun.descr = rs.getString("descr");
                albun.dt_compra = rs.getString("dt_compra");
                albun.dt_gravacao = rs.getString("dt_gravacao");
                albun.tipo_compra = rs.getString("tipo_compra");
                cds = new Cd[4];
                Cd c = new Cd();
                cds = c.listaCds(Integer.parseInt(rs.getString("albun_id")));
                albun.cds = cds;
                albuns[count] = albun;
                count++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return albuns;
    }
}
