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
 * @author iranilda
 */
public class Cd {
    public int cd_id;
    public Faixa[] faixas = new Faixa[50];
    
    public Cd[] listaCds(int albun_id){
        Cd[] cds = new Cd[4];
        String SQL = "";
        SQL += "SELECT " 
                + "cd_id "
                + "FROM "
                + "cds "
                + "WHERE "                
                + "albun_id = " + albun_id;
                
                
        ResultSet rscds = DbUtils.Lista(SQL);
        try{
            int count = 0;
            while (rscds.next()) { 
                Cd cd = new Cd();
                cd.cd_id = Integer.parseInt(rscds.getString("cd_id"));
                faixas = GetFaixas(Integer.parseInt(rscds.getString("cd_id")));
                cd.faixas = faixas;
                cds[count] = cd;
                count++;
                System.out.println("Quantidade de cds: " + count);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return cds;
    }
    public Faixa[] GetFaixas(int cd_id){
        Faixa[] faixas = new Faixa[40];
        String SQL = "";
        SQL += "SELECT " 
                + "f.faixa_id, "
                + "f.descr, "
                + "f.duracao, "
                + "c.nome "
                + "FROM "
                + "faixas f, "
                + "faixas_interpretes_comp fc, "
                + "composicoes cp, "
                + "compositores c "                
                + "WHERE "
                + "fc.faixa_id = f.faixa_id AND "
                + "fc.composicao_id = cp.composicao_id AND "
                + "cp.compositor_id = c.compositor_id AND "
                + "f.cd_id = " + cd_id;
        ResultSet rs = DbUtils.Lista(SQL);
        try{
            int count = 0;
            while (rs.next()) { 
                Faixa faixa = new Faixa();
                faixa.faixa_id = Integer.parseInt(rs.getString("faixa_id"));
                faixa.duracao = rs.getString("duracao");
                faixa.descr = rs.getString("descr");
                Compositor com = new Compositor();
                com.nome = rs.getString("nome");
                faixa.compositor = com;
                faixas[count] = faixa;
                count++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return faixas;
    }
}
