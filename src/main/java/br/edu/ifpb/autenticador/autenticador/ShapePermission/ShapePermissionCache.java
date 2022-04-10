package br.edu.ifpb.autenticador.autenticador.ShapePermission;

import br.edu.ifpb.autenticador.autenticador.domain.Permissions;

import java.util.HashMap;
import java.util.Map;

public class ShapePermissionCache {
    private Map<String, Permissions> cache = new HashMap<>();

    public ShapePermissionCache(){
        //ADM
        Permissions permissionADM = new Permissions();
        permissionADM.setAdminPermission(true);

        //Leitura
        Permissions permissionLeitura = new Permissions();
        permissionLeitura.setAdminPermission(false);
        permissionLeitura.setListPermission(true);
        permissionLeitura.setDeletePermission(false);
        permissionLeitura.setInsertPermission(false);
        permissionLeitura.setUpdatePermission(false);

        //Operador
        Permissions permissionOperador = new Permissions();
        permissionOperador.setAdminPermission(false);
        permissionOperador.setDeletePermission(false);
        permissionOperador.setListPermission(true);
        permissionOperador.setInsertPermission(true);
        permissionOperador.setUpdatePermission(true);

        //Default
        Permissions permissionDefault = new Permissions();
        permissionDefault.setAdminPermission(false);
        permissionDefault.setDeletePermission(false);
        permissionDefault.setListPermission(false);
        permissionDefault.setInsertPermission(false);
        permissionDefault.setUpdatePermission(false);

        //Add to mapList cache
        cache.put("administrador", permissionADM);
        cache.put("somenteLeitura", permissionLeitura);
        cache.put("operador", permissionOperador);
        cache.put("default", permissionDefault);

    }

    //Return Clone
    public Permissions get(String key){
        return new Permissions(cache.get(key));
    }
}
