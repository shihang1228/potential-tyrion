CREATE TABLE `contact` (
  `id` int(11) NOT NULL,
  `name` varchar(16) NOT NULL,
  `mobile` varchar(11) NOT NULL,
  `vpmn` varchar(6) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `home_address` varchar(255) DEFAULT NULL,
  `office_address` varchar(255) DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL,
  `job` varchar(16) DEFAULT NULL,
  `job_level` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 PACK_KEYS=0;

CREATE TABLE `contact_group` (
  `contact_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  PRIMARY KEY (`contact_id`),
  UNIQUE KEY `contact_id` (`contact_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 PACK_KEYS=0;

CREATE TABLE `group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `memo` varchar(11) CHARACTER SET utf8 DEFAULT NULL,
  `address` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 PACK_KEYS=0;