/*
SQLyog ‰ºÅ‰∏öÁâà - MySQL GUI v8.14 
MySQL - 5.7.22 : Database - security_oauth
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`security_oauth` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `security_oauth`;

/*Table structure for table `clientdetails` */

DROP TABLE IF EXISTS `clientdetails`;

CREATE TABLE `clientdetails` (
  `appId` varchar(256) NOT NULL,
  `resourceIds` varchar(256) DEFAULT NULL,
  `appSecret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `grantTypes` varchar(256) DEFAULT NULL,
  `redirectUrl` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additionalInformation` varchar(4096) DEFAULT NULL,
  `autoApproveScopes` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`appId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `clientdetails` */

/*Table structure for table `oauth_access_token` */

DROP TABLE IF EXISTS `oauth_access_token`;

CREATE TABLE `oauth_access_token` (
  `token_id` varchar(255) DEFAULT NULL,
  `token` longblob,
  `authentication_id` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  `authentication` longblob,
  `refresh_token` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `oauth_access_token` */

/*Table structure for table `oauth_approvals` */

DROP TABLE IF EXISTS `oauth_approvals`;

CREATE TABLE `oauth_approvals` (
  `userId` varchar(255) DEFAULT NULL,
  `clientId` varchar(255) DEFAULT NULL,
  `scope` varchar(255) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `expiresAt` datetime DEFAULT NULL,
  `lastModifiedAt` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `oauth_approvals` */

insert  into `oauth_approvals`(`userId`,`clientId`,`scope`,`status`,`expiresAt`,`lastModifiedAt`) values ('wx','heima_one','read','APPROVED','2020-11-10 10:45:39','2020-10-10 10:45:39'),('wx','heima_one',' write','APPROVED','2020-11-10 10:45:39','2020-10-10 10:45:39');

/*Table structure for table `oauth_client_details` */

DROP TABLE IF EXISTS `oauth_client_details`;

CREATE TABLE `oauth_client_details` (
  `client_id` varchar(255) NOT NULL,
  `resource_ids` varchar(255) DEFAULT NULL,
  `client_secret` varchar(255) DEFAULT NULL,
  `scope` varchar(255) DEFAULT NULL,
  `authorized_grant_types` varchar(255) DEFAULT NULL,
  `web_server_redirect_uri` varchar(255) DEFAULT NULL,
  `authorities` varchar(255) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(255) DEFAULT NULL,
  `autoapprove` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `oauth_client_details` */

insert  into `oauth_client_details`(`client_id`,`resource_ids`,`client_secret`,`scope`,`authorized_grant_types`,`web_server_redirect_uri`,`authorities`,`access_token_validity`,`refresh_token_validity`,`additional_information`,`autoapprove`) values ('heima_one','product_api','$2a$10$HpnWmwZrNhXkW0Qw7oB/nOcGe/fzSeZHYxL4UmqAle.5fUaWXQSVa','read, write','client_credentials,implicit,authorization_code,refresh_token,password','http://www.baidu.com',NULL,NULL,NULL,NULL,'false');

/*Table structure for table `oauth_client_token` */

DROP TABLE IF EXISTS `oauth_client_token`;

CREATE TABLE `oauth_client_token` (
  `token_id` varchar(255) DEFAULT NULL,
  `token` longblob,
  `authentication_id` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `client_id` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `oauth_client_token` */

/*Table structure for table `oauth_code` */

DROP TABLE IF EXISTS `oauth_code`;

CREATE TABLE `oauth_code` (
  `code` varchar(255) DEFAULT NULL,
  `authentication` varbinary(2550) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `oauth_code` */

insert  into `oauth_code`(`code`,`authentication`) values ('ytN60E','¨Ì\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2AuthenticationΩ@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationToken”™(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList¸%1µÏé\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0ÄÀ^˜\0L\0cq\0~\0xpsr\0java.util.ArrayListxÅ“ô«aù\0I\0sizexp\0\0\0w\0\0\0sr\0#cn.lx.security.doamin.SysPermissionœNöB∑òˆ\0L\0idt\0Ljava/lang/Integer;L\0parentIdt\0Ljava/lang/String;L\0permissionNameq\0~\0L\0\rpermissionUrlq\0~\0xpsr\0java.lang.Integer‚†§˜Åá8\0I\0valuexr\0java.lang.NumberÜ¨ïî‡ã\0\0xp\0\0\0t\00t\0PRODUCT_LISTt\0‰∫ßÂìÅÂàóË°®xq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0\Zxr\08org.springframework.security.oauth2.provider.BaseRequest6(z>£qiΩ\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0\Zxpt\0	heima_onesr\0%java.util.Collections$UnmodifiableMapÒ•®˛tıB\0L\0mq\0~\0xpsr\0java.util.HashMap⁄¡√`—\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\rresponse_typet\0codet\0	client_idq\0~\0xsr\0%java.util.Collections$UnmodifiableSetÄí—èõÄU\0\0xq\0~\0	sr\0java.util.LinkedHashSetÿl◊Zï›*\0\0xr\0java.util.HashSet∫DÖïñ∏∑4\0\0xpw\0\0\0?@\0\0\0\0\0t\0 writet\0readxsq\0~\0(w\0\0\0?@\0\0\0\0\0\0xsq\0~\0 ?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xt\0http://www.baidu.compsq\0~\0(w\0\0\0?@\0\0\0\0\0t\0product_apixsq\0~\0(w\0\0\0?@\0\0\0\0\0q\0~\0#xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0˛\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\05sr\0Horg.springframework.security.web.authentication.WebAuthenticationDetails\0\0\0\0\0\0˛\0L\0\rremoteAddressq\0~\0L\0	sessionIdq\0~\0xpt\00:0:0:0:0:0:0:1t\0 7CA94D9CF0E275DABDBCEC841B03FC1Epsr\0cn.lx.security.doamin.SysUser·r3ûjØ\0L\0authoritiesq\0~\0L\0idq\0~\0L\0passwordq\0~\0L\0statusq\0~\0L\0usernameq\0~\0xpsq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\0t\0<$2a$10$HpnWmwZrNhXkW0Qw7oB/nOcGe/fzSeZHYxL4UmqAle.5fUaWXQSVasq\0~\0\0\0\0t\0wx');

/*Table structure for table `oauth_refresh_token` */

DROP TABLE IF EXISTS `oauth_refresh_token`;

CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(255) DEFAULT NULL,
  `token` longblob,
  `authentication` longblob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `oauth_refresh_token` */

insert  into `oauth_refresh_token`(`token_id`,`token`,`authentication`) values ('1a72608c9deb026a9a4902e6665846c5','¨Ì\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/ﬂGcù–…∑\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens·\ncT‘^\0L\0valuet\0Ljava/lang/String;xpt\0$6029ae65-58c3-4b53-891d-3666266acbd8sr\0java.util.DatehjÅKYt\0\0xpw\0\0u™¥†≈x','¨Ì\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2AuthenticationΩ@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationToken”™(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList¸%1µÏé\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0ÄÀ^˜\0L\0cq\0~\0xpsr\0java.util.ArrayListxÅ“ô«aù\0I\0sizexp\0\0\0w\0\0\0sr\0#cn.lx.security.doamin.SysPermissionœNöB∑òˆ\0L\0idt\0Ljava/lang/Integer;L\0parentIdt\0Ljava/lang/String;L\0permissionNameq\0~\0L\0\rpermissionUrlq\0~\0xpsr\0java.lang.Integer‚†§˜Åá8\0I\0valuexr\0java.lang.NumberÜ¨ïî‡ã\0\0xp\0\0\0t\00t\0PRODUCT_LISTt\0‰∫ßÂìÅÂàóË°®xq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0\Zxr\08org.springframework.security.oauth2.provider.BaseRequest6(z>£qiΩ\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0\Zxpt\0	heima_onesr\0%java.util.Collections$UnmodifiableMapÒ•®˛tıB\0L\0mq\0~\0xpsr\0java.util.HashMap⁄¡√`—\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0	client_idt\0	heima_onet\0scopet\0readt\0usernamet\0wxxsr\0%java.util.Collections$UnmodifiableSetÄí—èõÄU\0\0xq\0~\0	sr\0java.util.LinkedHashSetÿl◊Zï›*\0\0xr\0java.util.HashSet∫DÖïñ∏∑4\0\0xpw\0\0\0?@\0\0\0\0\0q\0~\0\'xsq\0~\0-w\0\0\0?@\0\0\0\0\0\0xsq\0~\0 ?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0-w\0\0\0?@\0\0\0\0\0t\0product_apixsq\0~\0-w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0˛\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\07sr\0java.util.LinkedHashMap4¿N\\l¿˚\0Z\0accessOrderxq\0~\0 ?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0\"q\0~\0#q\0~\0&q\0~\0\'t\0\rclient_secrett\0123q\0~\0$q\0~\0%q\0~\0(q\0~\0)x\0psr\0cn.lx.security.doamin.SysUser·r3ûjØ\0L\0authoritiesq\0~\0L\0idq\0~\0L\0passwordq\0~\0L\0statusq\0~\0L\0usernameq\0~\0xpsq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\0t\0<$2a$10$HpnWmwZrNhXkW0Qw7oB/nOcGe/fzSeZHYxL4UmqAle.5fUaWXQSVasq\0~\0\0\0\0t\0wx'),('cf287aac8d9c91a107d5e03286fd669e','¨Ì\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/ﬂGcù–…∑\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens·\ncT‘^\0L\0valuet\0Ljava/lang/String;xpt\0$911e1501-05f6-42cc-965d-5df6189d7f5csr\0java.util.DatehjÅKYt\0\0xpw\0\0u™˜\"=x','¨Ì\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2AuthenticationΩ@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationToken”™(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList¸%1µÏé\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0ÄÀ^˜\0L\0cq\0~\0xpsr\0java.util.ArrayListxÅ“ô«aù\0I\0sizexp\0\0\0w\0\0\0sr\0#cn.lx.security.domain.SysPermissionØ9Ëúc≥+ø\0L\0idt\0Ljava/lang/Integer;L\0parentIdt\0Ljava/lang/String;L\0permissionNameq\0~\0L\0\rpermissionUrlq\0~\0xpsr\0java.lang.Integer‚†§˜Åá8\0I\0valuexr\0java.lang.NumberÜ¨ïî‡ã\0\0xp\0\0\0t\00t\0PRODUCT_LISTt\0‰∫ßÂìÅÂàóË°®xq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0\Zxr\08org.springframework.security.oauth2.provider.BaseRequest6(z>£qiΩ\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0\Zxpt\0	heima_onesr\0%java.util.Collections$UnmodifiableMapÒ•®˛tıB\0L\0mq\0~\0xpsr\0java.util.HashMap⁄¡√`—\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0	client_idt\0	heima_onet\0scopet\0readt\0usernamet\0wxxsr\0%java.util.Collections$UnmodifiableSetÄí—èõÄU\0\0xq\0~\0	sr\0java.util.LinkedHashSetÿl◊Zï›*\0\0xr\0java.util.HashSet∫DÖïñ∏∑4\0\0xpw\0\0\0?@\0\0\0\0\0q\0~\0\'xsq\0~\0-w\0\0\0?@\0\0\0\0\0\0xsq\0~\0 ?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0-w\0\0\0?@\0\0\0\0\0t\0product_apixsq\0~\0-w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0˛\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\07sr\0java.util.LinkedHashMap4¿N\\l¿˚\0Z\0accessOrderxq\0~\0 ?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0\"q\0~\0#q\0~\0&q\0~\0\'t\0\rclient_secrett\0123q\0~\0$q\0~\0%q\0~\0(q\0~\0)x\0psr\0cn.lx.security.domain.SysUser;Úˆ•$Y\0L\0authoritiesq\0~\0L\0idq\0~\0L\0passwordq\0~\0L\0statusq\0~\0L\0usernameq\0~\0xpsq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\0t\0<$2a$10$HpnWmwZrNhXkW0Qw7oB/nOcGe/fzSeZHYxL4UmqAle.5fUaWXQSVasq\0~\0\0\0\0t\0wx');

/*Table structure for table `persistent_logins` */

DROP TABLE IF EXISTS `persistent_logins`;

CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `persistent_logins` */

insert  into `persistent_logins`(`username`,`series`,`token`,`last_used`) values ('lx','Q9EsuP3zpjqPs9VYsTOo/w==','XWPcsDTq8VlRz++79REBrA==','2020-10-06 18:00:57'),('lx','wVImYJxB/5UJLjKSR6iOCg==','iYDBVrQvgbVdwqxn3dF1Rw==','2020-10-06 17:42:54');

/*Table structure for table `sys_permission` */

DROP TABLE IF EXISTS `sys_permission`;

CREATE TABLE `sys_permission` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ÁºñÂè∑',
  `permission_NAME` varchar(30) DEFAULT NULL COMMENT 'ËèúÂçïÂêçÁß∞',
  `permission_url` varchar(100) DEFAULT NULL COMMENT 'ËèúÂçïÂú∞ÂùÄ',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT 'Áà∂ËèúÂçïid',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `sys_permission` */

insert  into `sys_permission`(`ID`,`permission_NAME`,`permission_url`,`parent_id`) values (1,'ORDER_LIST','ËÆ¢ÂçïÂàóË°®',0),(2,'PRODUCT_LIST','‰∫ßÂìÅÂàóË°®',0);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ÁºñÂè∑',
  `ROLE_NAME` varchar(30) DEFAULT NULL COMMENT 'ËßíËâ≤ÂêçÁß∞',
  `ROLE_DESC` varchar(60) DEFAULT NULL COMMENT 'ËßíËâ≤ÊèèËø∞',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`ID`,`ROLE_NAME`,`ROLE_DESC`) values (1,'ROLE_USER','Âü∫Á°ÄÁî®Êà∑'),(2,'ROLE_ADMIN','ÁÆ°ÁêÜÂëò'),(3,'ROLE_PRODUCT','‰∫ßÂìÅÁÆ°ÁêÜÂëò'),(4,'ROLE_ORDER','ËÆ¢ÂçïÁÆ°ÁêÜÂëò');

/*Table structure for table `sys_role_permission` */

DROP TABLE IF EXISTS `sys_role_permission`;

CREATE TABLE `sys_role_permission` (
  `RID` int(11) NOT NULL COMMENT 'ËßíËâ≤ÁºñÂè∑',
  `PID` int(11) NOT NULL COMMENT 'ÊùÉÈôêÁºñÂè∑',
  PRIMARY KEY (`RID`,`PID`),
  KEY `FK_Reference_12` (`PID`),
  CONSTRAINT `FK_Reference_11` FOREIGN KEY (`RID`) REFERENCES `sys_role` (`ID`),
  CONSTRAINT `FK_Reference_12` FOREIGN KEY (`PID`) REFERENCES `sys_permission` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role_permission` */

insert  into `sys_role_permission`(`RID`,`PID`) values (2,1),(3,1),(2,2),(4,2);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL COMMENT 'Áî®Êà∑ÂêçÁß∞',
  `password` varchar(120) NOT NULL COMMENT 'ÂØÜÁ†Å',
  `status` int(1) DEFAULT '1' COMMENT '1ÂºÄÂêØ0ÂÖ≥Èó≠',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`username`,`password`,`status`) values (1,'lx','$2a$10$HpnWmwZrNhXkW0Qw7oB/nOcGe/fzSeZHYxL4UmqAle.5fUaWXQSVa',1),(2,'wx','$2a$10$HpnWmwZrNhXkW0Qw7oB/nOcGe/fzSeZHYxL4UmqAle.5fUaWXQSVa',1);

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `UID` int(11) NOT NULL COMMENT 'Áî®Êà∑ÁºñÂè∑',
  `RID` int(11) NOT NULL COMMENT 'ËßíËâ≤ÁºñÂè∑',
  PRIMARY KEY (`UID`,`RID`),
  KEY `FK_Reference_10` (`RID`),
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`RID`) REFERENCES `sys_role` (`ID`),
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`UID`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`UID`,`RID`) values (1,3),(2,4);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
