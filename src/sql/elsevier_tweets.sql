CREATE TABLE `tweets` (
  `id` bigint NOT NULL,
  `date` datetime DEFAULT NULL,
  `text` text CHARACTER SET utf8,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
