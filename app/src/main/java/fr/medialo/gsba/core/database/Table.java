package fr.medialo.gsba.core.database;

enum Table {

//    Status("CREATE TABLE `status` (\n" +
//            "  `id` INTEGER NOT NULL PRIMARY KEY,\n" +
//            "  `name` varchar(30) DEFAULT NULL\n" +
//            ");"),
    Files("CREATE TABLE `files` (\n" +
            "  `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "  `created` datetime NOT NULL,\n" +
            "  `modified` datetime NOT NULL,\n" +
            "  `statu_id` INTEGER NOT NULL,\n" +
            "  `totalcost` double(6,3) DEFAULT NULL,\n" +
            "  `commentary` varchar(1000) DEFAULT NULL\n" +
//            "   FOREIGN KEY (`statu_id`) REFERENCES status(id)"+
            ");"),
    FeeLines("CREATE TABLE `feeslines` (\n" +
            "  `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "  `sublistfee_id` INTEGER NOT NULL,\n" +
            "  `quantity` INTEGER NOT NULL DEFAULT 1,\n" +
            "  `receipt` varchar(200) DEFAULT NULL,\n" +
            "  `created` datetime NOT NULL,\n" +
            "  `file_id` INTEGER NOT NULL,\n" +
            "  `statu_id` INTEGER NOT NULL DEFAULT 1,\n" +
            "  FOREIGN KEY (`file_id`) REFERENCES files(id)"+
//            "  FOREIGN KEY (`statu_id`) REFERENCES status(id),"+
//            "  FOREIGN KEY (`sublistfee_id`) REFERENCES sublistfees(id)"+
            ");"),
    ExclFeeLines("CREATE TABLE `exclfeeslines` (\n" +
            "  `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "  `name` varchar(50) NOT NULL,\n" +
            "  `cost` double NOT NULL,\n" +
            "  `refund` double DEFAULT 0,\n" +
            "  `created` datetime DEFAULT NULL,\n" +
            "  `receipt` varchar(200) DEFAULT NULL,\n" +
            "  `file_id` INTEGER NOT NULL,\n" +
            "  `statu_id` INTEGER NOT NULL DEFAULT 1\n" +
            ");"),

//    Category("CREATE TABLE `listfees` (\n" +
//            "  `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
//            "  `name` varchar(50) DEFAULT NULL\n" +
//            ");"),
//
//    SubCategory("CREATE TABLE `sublistfees` (\n" +
//            "  `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
//            "  `name` varchar(50) NOT NULL,\n" +
//            "  `cost` double NOT NULL,\n" +
//            "  `listfee_id` INTEGER NOT NULL,\n" +
//            " FOREIGN KEY (`listfee_id`) REFERENCES `listfees` (`id`)"+
//            ");"),


//    DEFAULT_VALUES("" +
//            "INSERT INTO `status` (`id`, `name`) VALUES\n" +
//            "(1, 'En attente'),\n" +
//            "(2, 'Validé'),\n" +
//            "(3, 'Refusé'),\n" +
//            "(4, 'A compléter')\n" +
//            ";")
    ;

    String sql;

    Table(String sql) {
        this.sql=sql;
    }

    public String getSql() {
        return sql;
    }
}


