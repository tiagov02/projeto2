open module com.example.bd {
    exports com.example.bd.CRUD.exceptions;
    exports com.example.bd.Entity;
    exports com.example.bd.Encrypt;
    exports com.example.bd.CRUD;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires java.sql;
}