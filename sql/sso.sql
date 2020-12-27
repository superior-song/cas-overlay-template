/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : sso

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2020-12-27 16:03:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for regexregisteredservice
-- ----------------------------
DROP TABLE IF EXISTS `regexregisteredservice`;
CREATE TABLE `regexregisteredservice` (
  `expression_type` varchar(50) NOT NULL DEFAULT 'regex',
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `access_strategy` longblob,
  `attribute_release` longblob,
  `description` varchar(255) DEFAULT NULL,
  `evaluation_order` int(11) NOT NULL,
  `expiration_policy` longblob,
  `informationUrl` varchar(255) DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `logout_type` int(11) DEFAULT NULL,
  `logout_url` varchar(255) DEFAULT NULL,
  `mfa_policy` longblob,
  `name` varchar(255) NOT NULL,
  `privacyUrl` varchar(255) DEFAULT NULL,
  `proxy_policy` longblob,
  `public_key` longblob,
  `required_handlers` longblob,
  `responseType` varchar(255) DEFAULT NULL,
  `serviceId` varchar(255) NOT NULL,
  `theme` varchar(255) DEFAULT NULL,
  `username_attr` longblob,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of regexregisteredservice
-- ----------------------------

-- ----------------------------
-- Table structure for regexregisteredserviceproperty
-- ----------------------------
DROP TABLE IF EXISTS `regexregisteredserviceproperty`;
CREATE TABLE `regexregisteredserviceproperty` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `property_values` longblob,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of regexregisteredserviceproperty
-- ----------------------------

-- ----------------------------
-- Table structure for registeredserviceimplcontact
-- ----------------------------
DROP TABLE IF EXISTS `registeredserviceimplcontact`;
CREATE TABLE `registeredserviceimplcontact` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `department` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of registeredserviceimplcontact
-- ----------------------------

-- ----------------------------
-- Table structure for registeredserviceimpl_props
-- ----------------------------
DROP TABLE IF EXISTS `registeredserviceimpl_props`;
CREATE TABLE `registeredserviceimpl_props` (
  `AbstractRegisteredService_id` bigint(20) NOT NULL,
  `properties_id` bigint(20) NOT NULL,
  `properties_KEY` varchar(255) NOT NULL,
  PRIMARY KEY (`AbstractRegisteredService_id`,`properties_KEY`),
  UNIQUE KEY `UK_i2mjaqjwxpvurc6aefjkx5x97` (`properties_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of registeredserviceimpl_props
-- ----------------------------

-- ----------------------------
-- Table structure for registeredservice_contacts
-- ----------------------------
DROP TABLE IF EXISTS `registeredservice_contacts`;
CREATE TABLE `registeredservice_contacts` (
  `AbstractRegisteredService_id` bigint(20) NOT NULL,
  `contacts_id` bigint(20) NOT NULL,
  `contacts_ORDER` int(11) NOT NULL,
  PRIMARY KEY (`AbstractRegisteredService_id`,`contacts_ORDER`),
  UNIQUE KEY `UK_s7mf4a23wejqx62tt4vh3tgwi` (`contacts_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of registeredservice_contacts
-- ----------------------------

-- ----------------------------
-- Table structure for secu_user
-- ----------------------------
DROP TABLE IF EXISTS `secu_user`;
CREATE TABLE `secu_user` (
  `ID` int(11) NOT NULL,
  `USER_NAME` varchar(200) DEFAULT NULL,
  `PASSWORD` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of secu_user
-- ----------------------------
