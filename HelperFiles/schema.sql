CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `active` tinyint NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `roles` varchar(45) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci