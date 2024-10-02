package edu.sm.frame;

import java.sql.Connection;
import java.util.List;

public interface Dao<K,V> {
    V insert(V v, Connection con) throws  Exception;
    //Service에서 connection 받아와서 Dao 주려고
    V update(V v, Connection con) throws Exception;
    Boolean delete(K k, Connection con) throws Exception;
    V select(K k, Connection con) throws Exception;
    List<V> select(Connection con) throws Exception;


}
