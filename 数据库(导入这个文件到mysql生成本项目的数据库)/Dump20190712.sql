CREATE DATABASE  IF NOT EXISTS `demo` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `demo`;
-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: demo
-- ------------------------------------------------------
-- Server version	5.7.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tb_account`
--

DROP TABLE IF EXISTS `tb_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_account` (
  `account_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '账户编号，主键，自动增长',
  `user_id` int(11) NOT NULL COMMENT '用户编号(外键)',
  `money` double NOT NULL COMMENT '账户金额',
  PRIMARY KEY (`account_id`),
  KEY `user_id_3_idx` (`user_id`),
  KEY `user_idd_idx` (`user_id`),
  CONSTRAINT `user_idd` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_account`
--

LOCK TABLES `tb_account` WRITE;
/*!40000 ALTER TABLE `tb_account` DISABLE KEYS */;
INSERT INTO `tb_account` VALUES (1,1,1000),(2,2,988);
/*!40000 ALTER TABLE `tb_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_address`
--

DROP TABLE IF EXISTS `tb_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_address` (
  `address_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '地址编号，主键，自增',
  `user_id` int(11) NOT NULL COMMENT '用户编号（外键）',
  `full_address` varchar(200) NOT NULL COMMENT '详细地址',
  `phone` varchar(20) NOT NULL COMMENT '联系电话',
  `zip_code` varchar(7) NOT NULL DEFAULT '100000' COMMENT '邮编',
  `name` varchar(50) NOT NULL COMMENT '联系人姓名',
  `state` int(2) unsigned NOT NULL DEFAULT '0' COMMENT '1:默认 0:非默认',
  PRIMARY KEY (`address_id`),
  KEY `user_id_2_idx` (`user_id`),
  CONSTRAINT `user_id_2` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_address`
--

LOCK TABLES `tb_address` WRITE;
/*!40000 ALTER TABLE `tb_address` DISABLE KEYS */;
INSERT INTO `tb_address` VALUES (1,2,'河北省张家口市宣化区幸福小区110','18001309999','075110','张铠驿',1),(3,2,'北京市昌平区昌平县城开心小区222','18001309999','102200','张铠驿',0),(13,2,'qwe','12','12','z',0);
/*!40000 ALTER TABLE `tb_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_admin_yrh`
--

DROP TABLE IF EXISTS `tb_admin_yrh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_admin_yrh` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(50) NOT NULL,
  `admin_password` varchar(50) NOT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_admin_yrh`
--

LOCK TABLES `tb_admin_yrh` WRITE;
/*!40000 ALTER TABLE `tb_admin_yrh` DISABLE KEYS */;
INSERT INTO `tb_admin_yrh` VALUES (1,'1','1'),(2,'y','y'),(3,'yuan','1');
/*!40000 ALTER TABLE `tb_admin_yrh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_brand`
--

DROP TABLE IF EXISTS `tb_brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_brand` (
  `brand_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商家编号，主键，自动增长（如乐视？旗舰店）',
  `name` varchar(50) NOT NULL COMMENT '联系人姓名',
  `phone` varchar(20) NOT NULL COMMENT '电话',
  `email` varchar(50) NOT NULL COMMENT '邮箱',
  `address` varchar(200) NOT NULL COMMENT '公司详细地址',
  `state` int(1) unsigned NOT NULL DEFAULT '0' COMMENT '0:合作  1:取消合作',
  `contact` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`brand_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_brand`
--

LOCK TABLES `tb_brand` WRITE;
/*!40000 ALTER TABLE `tb_brand` DISABLE KEYS */;
INSERT INTO `tb_brand` VALUES (1,'三只松鼠','13212341234','432424@163.com','安徽省芜湖市弋江区芜湖高新技术产业开发区',0,'张三'),(2,'周黑鸭','13289054543','5435345@163.com','安徽',0,'李四'),(3,'旺旺','13289054543','5435345@163.com','安徽',0,'王五'),(4,'徐福记','13289054543','5435345@163.com','安徽',0,'张三'),(5,'好吃点','13289054543','5435345@163.com','安徽省芜湖市弋江区芜湖高新技术产业开发区',0,'李四'),(6,'稻香村','13289054543','5435345@163.com','安徽',0,'张三'),(7,'乐事','13289054543','5435345@163.com','安徽',0,'李四'),(8,'良品铺子','13289054543','5435345@163.com','安徽',0,'王五'),(9,'卫龙','13289054543','5435345@163.com','安徽省芜湖市弋江区芜湖高新技术产业开发区',1,'张三'),(10,'口水娃','13289054543','5435345@163.com','安徽',0,'李四');
/*!40000 ALTER TABLE `tb_brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_cart`
--

DROP TABLE IF EXISTS `tb_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_cart` (
  `cart_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '购物车编号，主键，自动增长',
  `user_id` int(11) NOT NULL COMMENT '用户编号(外键)',
  `flavor_id` int(11) NOT NULL COMMENT '口味编号',
  `commodity_id` int(11) NOT NULL COMMENT '商品编号',
  `quantity` int(11) DEFAULT NULL COMMENT '商品名数量',
  PRIMARY KEY (`cart_id`),
  KEY `user_id_10_idx` (`user_id`),
  KEY `flacor_id_10_idx` (`flavor_id`),
  KEY `commodity_id_10_idx` (`commodity_id`),
  CONSTRAINT `commodity_id_10` FOREIGN KEY (`commodity_id`) REFERENCES `tb_commodity` (`commodity_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `flavor_id_10` FOREIGN KEY (`flavor_id`) REFERENCES `tb_flavor` (`flavor_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_id_10` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_cart`
--

LOCK TABLES `tb_cart` WRITE;
/*!40000 ALTER TABLE `tb_cart` DISABLE KEYS */;
INSERT INTO `tb_cart` VALUES (24,2,8,1,6),(26,2,9,1,4),(27,2,2,9,1),(28,2,1,6,3),(29,2,11,3,20),(33,2,1,14,1),(48,2,2,14,1);
/*!40000 ALTER TABLE `tb_cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_commodity`
--

DROP TABLE IF EXISTS `tb_commodity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_commodity` (
  `commodity_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品编号，主键，自动增长',
  `second_category_id` int(11) NOT NULL COMMENT '二级类别编号（外键） ',
  `brand_id` int(11) NOT NULL COMMENT '品牌编号(外键)',
  `cname` varchar(50) NOT NULL COMMENT '商品名称',
  `promotional_price` double DEFAULT NULL COMMENT '促销价',
  `original_price` double NOT NULL COMMENT '原价',
  `description` varchar(500) NOT NULL COMMENT '商品描述',
  `img` varchar(30) NOT NULL DEFAULT 'default.jpg' COMMENT '商品图片名',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `type` varchar(50) DEFAULT NULL COMMENT '产品类型',
  `product_area` varchar(50) DEFAULT NULL COMMENT '原料产地',
  `product_place` varchar(50) DEFAULT NULL COMMENT '产地',
  `product_specificat` varchar(50) DEFAULT NULL COMMENT '产品规格',
  `expiration_date` varchar(50) DEFAULT NULL COMMENT '保质期',
  `usage` varchar(50) DEFAULT NULL COMMENT '食用方法',
  `storage_method` varchar(50) DEFAULT NULL COMMENT '存储方法',
  `standard_number` varchar(50) DEFAULT NULL COMMENT '产品标准号',
  `license_number` varchar(50) DEFAULT NULL COMMENT '生产许可号',
  PRIMARY KEY (`commodity_id`),
  KEY `second_category_id_7_idx` (`second_category_id`),
  KEY `brand_id` (`brand_id`),
  CONSTRAINT `brand_id_7` FOREIGN KEY (`brand_id`) REFERENCES `tb_brand` (`brand_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `second_category_id_7` FOREIGN KEY (`second_category_id`) REFERENCES `tb_second_category` (`second_category_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_commodity`
--

LOCK TABLES `tb_commodity` WRITE;
/*!40000 ALTER TABLE `tb_commodity` DISABLE KEYS */;
INSERT INTO `tb_commodity` VALUES (1,0,7,'乐事（Lay’s）零食 休闲食品 大波浪薯片 ',13.5,13.5,'品牌： 乐事（Lay\'s）\r\n商品名称：乐事大波浪薯片 铁板鱿鱼味 145g商品毛重：160.00g商品产地：上海市国产/进口：国产分类：膨化食品','sp01.jpg','2019-05-28 16:59:00','膨化类食品','义乌','上海市','160g','180天','开袋即食','干燥环境','GB/T 22165','QS4201 1801 0226'),(2,0,7,'乐事（Lay’s）薯片 休闲零食 145g',10.5,10.5,'品牌： 乐事（Lay\'s）\r\n商品名称：乐事（Lay\'s） 乐事大波浪薯片碳烤五花肉味145g/袋休闲食品小吃零食商品编号：28553162264毛重：160.00g国产/进口：国产规格：200g以下','sp02.jpg','2019-05-13 16:49:32','膨化类食品','义乌','上海市','160g','180 天','开袋即食','常温避光','LS/T 3211 GB 17400','SC10633019702613'),(3,0,1,'休闲怀旧零食品掌心脆干吃方便面',1,1,'产品参数：\r\n\r\n生产许可证编号：SC10633019702613产品标准号：LS/T 3211 GB 17400厂名：杭州统一企业有限公司厂址：浙江省杭州市杭州大江东产业集聚区三丰路301号厂家联系方式：4007000600配料表：小麦粉、精炼植物油（含维生素E）、白砂糖、食用盐等储藏方法：常温避光保质期：180 天食品添加剂：见包装品牌: 统一系列: 小当家干脆面','sp03.jpg','2019-05-14 10:17:27','膨化类食品','浙江省杭州市杭州大江东产业集聚区三丰路301号','杭州市','120g','180 天','开袋即食','常温避光','LS/T 3211 GB 17400','SC10633019702613'),(4,0,1,'冬己饼干咸蛋黄黑糖夹心麦芽饼网红办公室休闲小零食',25.5,25.5,'5464','sp04.jpg','2019-05-28 16:59:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,0,1,'曲奇饼干进口黄油糕点办公零食',30,30,'5464','sp05.jpg','2019-05-28 16:59:00',NULL,NULL,NULL,NULL,'180 天',NULL,NULL,NULL,NULL),(6,0,1,'白色恋人饼干日本进口零食北海道夹心饼干',15.5,15.5,'5464','sp06.jpg','2019-05-28 16:59:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(7,0,1,'黄金香厦门老字号鼓浪屿特产油酥肉松营养零食',68,68,'5464','sp07.jpg','2019-05-28 16:59:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(8,0,1,'泡椒凤爪500g小包装泡凤爪散装鸡爪子休闲成人零食',13.5,13.5,'5464','sp08.jpg','2019-05-28 16:59:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(9,0,1,'牛肉干肉脯零食小吃牛筋牛肉粒',54,54,'5464','sp09.jpg','2019-05-28 16:59:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(10,0,1,'营养早餐口袋面包网红零食饼干蛋糕乳酸菌小伴侣面包',28.9,28.9,'5464','sp10.jpg','2019-05-28 16:59:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(11,0,1,'休闲零食膨化小吃方便面干脆面小贱拉面丸子85g/袋',10.9,10.9,'5464','sp11.jpg','2019-05-28 16:59:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(12,0,1,'饼干糕点网红零食台湾风味一口凤梨酥300g/袋',39.5,39.5,'5464','sp12.jpg','2019-05-28 16:59:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(13,0,1,'肉食海味即食鱼干小鱼仔香酥小黄鱼96g/袋',9.9,9.9,'5464','sp13.jpg','2019-05-28 16:59:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(14,0,1,'薯片 休闲零食 休闲怀旧零食 送礼零食',12,12,'5464','sp14.jpg','2019-05-28 16:59:00','膨化类食品','浙江省杭州市杭州大江东产业集聚区三丰路301号','杭州市','160g','180 天','开袋即食','常温避光','LS/T 3211 GB 17400','SC10633019702613'),(15,0,1,'巨型网红零食大礼包2.0升级版30包送礼肩扛大零食猪饲料',88,88,'5464','001.jpg','2019-05-28 16:59:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(16,0,1,'芒果干116g休闲零食特产蜜饯果脯水果干',23,23,'5464','002.jpg','2019-05-28 16:59:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(17,0,1,' 坚果炒货休闲零食 送礼零食',23,23,'5464','003.jpg','2019-05-28 16:59:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(18,0,1,' 每日坚果混合果仁 送礼零食',23,23,'5464','004.jpg','2019-05-28 16:59:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(19,0,1,' 每日坚果混合果仁（25g*30包）750g/盒 送礼零食礼盒',79.9,79.9,'5464','005.jpg','2019-05-28 16:59:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(20,0,1,'坚果炒货零食每日坚果手剥东北开口松子',23,23,'5464','006.jpg','2019-05-15 17:15:45',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `tb_commodity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_evaluate`
--

DROP TABLE IF EXISTS `tb_evaluate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_evaluate` (
  `evaluate_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号，主键自动增长',
  `user_id` int(11) NOT NULL COMMENT '用户编号(外键)',
  `order_id` int(11) NOT NULL COMMENT '订单编号(外键)',
  `createtime` datetime DEFAULT NULL COMMENT '评论时间',
  `content` varchar(500) DEFAULT NULL COMMENT '内容',
  PRIMARY KEY (`evaluate_id`),
  KEY `user_id_6_idx` (`user_id`),
  KEY `order_id_66_idx` (`order_id`),
  CONSTRAINT `order_id_66` FOREIGN KEY (`order_id`) REFERENCES `tb_order` (`order_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_id_6` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_evaluate`
--

LOCK TABLES `tb_evaluate` WRITE;
/*!40000 ALTER TABLE `tb_evaluate` DISABLE KEYS */;
INSERT INTO `tb_evaluate` VALUES (1,2,1,'2019-06-28 16:59:00','我是水军，产品很好'),(6,2,1,'2019-06-28 16:57:00','我是水军，产品很好(5毛一条，括号内删除)');
/*!40000 ALTER TABLE `tb_evaluate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_first_category`
--

DROP TABLE IF EXISTS `tb_first_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_first_category` (
  `first_category_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类编号，主键，自动增长',
  `first_category_name` varchar(20) NOT NULL COMMENT '类别名称',
  PRIMARY KEY (`first_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_first_category`
--

LOCK TABLES `tb_first_category` WRITE;
/*!40000 ALTER TABLE `tb_first_category` DISABLE KEYS */;
INSERT INTO `tb_first_category` VALUES (1,'休闲食品'),(2,'粮油调味'),(3,'进口食品'),(4,'营养辅食'),(5,'饮料冲调'),(6,'个性零食'),(7,'海味/河鲜'),(8,'花茶/果茶'),(9,'饼干蛋糕'),(10,'肉干肉脯'),(11,'糖果/巧克力'),(12,'坚果炒货'),(13,'蜜饯果干'),(14,'熟食腊味'),(15,'膨化食品'),(16,'方便食品'),(17,'食用油'),(18,'杂粮'),(19,'南北干货'),(20,'调味品'),(21,'烘焙原料'),(22,'米'),(23,'糖果'),(24,'饼干蛋糕'),(25,'休闲零食'),(26,'巧克力'),(27,'方便食品'),(28,'咖啡豆/咖啡粉'),(29,'冲调品'),(30,'米面调油'),(31,'饮料'),(32,'牛奶'),(33,'油'),(34,'宝宝零食'),(35,'果汁/果泥'),(36,'米粉/菜粉'),(37,'钙铁锌/维生素'),(38,'面条/粥'),(39,'清火/开胃'),(40,'益生菌'),(41,'冲饮谷物'),(42,'饮料'),(43,'牛奶乳品'),(44,'咖啡/奶茶'),(45,'成人奶粉'),(46,'蜂蜜/柚子茶'),(47,'0-25'),(48,'26-50'),(49,'50以上'),(50,'海鲜制品'),(51,'其他'),(52,'A');
/*!40000 ALTER TABLE `tb_first_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_flavor`
--

DROP TABLE IF EXISTS `tb_flavor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_flavor` (
  `flavor_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '口味编号，主键，自动增长',
  `flavor_name` varchar(30) NOT NULL COMMENT '口味名称',
  PRIMARY KEY (`flavor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_flavor`
--

LOCK TABLES `tb_flavor` WRITE;
/*!40000 ALTER TABLE `tb_flavor` DISABLE KEYS */;
INSERT INTO `tb_flavor` VALUES (1,'混合口味'),(2,'香辣'),(3,'香葱'),(4,'红烧牛肉'),(5,'五香牛肉'),(6,'铁板鱿鱼'),(7,'芝士培根'),(8,'番茄'),(9,'经典原味'),(10,'鸡翅'),(11,'奶油'),(12,'麻辣');
/*!40000 ALTER TABLE `tb_flavor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_flavor_commodity`
--

DROP TABLE IF EXISTS `tb_flavor_commodity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_flavor_commodity` (
  `flavor_commodity_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号，主键，自增',
  `flavor_id` int(11) NOT NULL COMMENT '口味编号（外键）',
  `commodity_id` int(11) NOT NULL COMMENT '商品编号（外键）',
  `stock` int(11) DEFAULT NULL COMMENT '库存',
  PRIMARY KEY (`flavor_commodity_id`),
  KEY `flavor_id_9_idx` (`flavor_id`),
  KEY `commodity_id_9_idx` (`commodity_id`),
  CONSTRAINT `commodity_id_9` FOREIGN KEY (`commodity_id`) REFERENCES `tb_commodity` (`commodity_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `flavor_id_9` FOREIGN KEY (`flavor_id`) REFERENCES `tb_flavor` (`flavor_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_flavor_commodity`
--

LOCK TABLES `tb_flavor_commodity` WRITE;
/*!40000 ALTER TABLE `tb_flavor_commodity` DISABLE KEYS */;
INSERT INTO `tb_flavor_commodity` VALUES (1,1,14,44),(2,2,14,54),(3,3,14,54),(4,1,9,63),(5,2,9,42),(6,9,3,54),(7,10,3,55),(8,11,3,11),(9,8,1,49),(10,9,1,57),(11,8,2,22),(12,9,2,66),(13,1,4,39),(14,1,5,31),(15,1,6,63),(16,1,7,2),(17,1,8,4),(18,1,9,66),(19,1,10,3),(20,1,11,7),(21,1,12,55),(22,1,13,886),(23,1,15,77),(24,1,16,33),(25,1,17,65),(26,1,18,54),(27,1,19,5),(28,1,20,6);
/*!40000 ALTER TABLE `tb_flavor_commodity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_order`
--

DROP TABLE IF EXISTS `tb_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单编号，主键',
  `user_id` int(11) NOT NULL COMMENT '用户编号(外键)',
  `address_id` int(11) NOT NULL COMMENT '地址编号(外键)',
  `totalprice` double DEFAULT NULL COMMENT '总价',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `ordertime` datetime DEFAULT NULL COMMENT '下单时间',
  `state` int(1) unsigned NOT NULL DEFAULT '0' COMMENT '订单状态：0:待付款  1:待发货  2:待收货 3:待评价',
  PRIMARY KEY (`order_id`),
  KEY `FK_Reference_6` (`user_id`),
  KEY `adress_id_11_idx` (`address_id`),
  KEY `address_id_idx` (`address_id`),
  CONSTRAINT `address_id` FOREIGN KEY (`address_id`) REFERENCES `tb_address` (`address_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_ud_11` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_order`
--

LOCK TABLES `tb_order` WRITE;
/*!40000 ALTER TABLE `tb_order` DISABLE KEYS */;
INSERT INTO `tb_order` VALUES (1,2,3,162,'送小礼物','2019-05-28 10:28:00',1),(2,2,1,245.5,'送小礼物','2019-05-28 10:51:37',1),(3,2,1,79.5,'','2019-05-28 10:54:01',1);
/*!40000 ALTER TABLE `tb_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_orders_detail`
--

DROP TABLE IF EXISTS `tb_orders_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_orders_detail` (
  `detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `commodity_id` int(11) NOT NULL COMMENT '商品编号（外键）',
  `order_id` int(11) NOT NULL COMMENT '订单编号（外键)',
  `flavor_id` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL COMMENT '商品数量',
  `price` double DEFAULT NULL COMMENT '单价',
  PRIMARY KEY (`detail_id`),
  KEY `commodity_id_12_idx` (`commodity_id`),
  KEY `flavor_id_13_idx` (`flavor_id`),
  KEY `order_id_133_idx` (`flavor_id`,`commodity_id`,`order_id`),
  KEY `order_id_133_idx1` (`order_id`),
  CONSTRAINT `commodity_id_13` FOREIGN KEY (`commodity_id`) REFERENCES `tb_commodity` (`commodity_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `flavor_id_13` FOREIGN KEY (`flavor_id`) REFERENCES `tb_flavor` (`flavor_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `order_id_133` FOREIGN KEY (`order_id`) REFERENCES `tb_order` (`order_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_orders_detail`
--

LOCK TABLES `tb_orders_detail` WRITE;
/*!40000 ALTER TABLE `tb_orders_detail` DISABLE KEYS */;
INSERT INTO `tb_orders_detail` VALUES (26,1,1,8,2,13.5),(27,9,1,8,1,54),(28,4,2,8,1,25.5),(29,1,2,8,3,13.5),(30,9,2,8,1,54),(31,6,2,8,1,15.5),(32,4,3,8,1,25.5),(33,9,3,8,1,54);
/*!40000 ALTER TABLE `tb_orders_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_record`
--

DROP TABLE IF EXISTS `tb_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_record` (
  `record_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '账户编号，主键，自动增长',
  `user_id` int(11) NOT NULL COMMENT '用户编号（外键）',
  `consume` double NOT NULL COMMENT '账户余额变动',
  PRIMARY KEY (`record_id`),
  KEY `user_id_3_idx` (`user_id`),
  CONSTRAINT `user_id_3` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_record`
--

LOCK TABLES `tb_record` WRITE;
/*!40000 ALTER TABLE `tb_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_second_category`
--

DROP TABLE IF EXISTS `tb_second_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_second_category` (
  `second_category_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '二级类编号（主键）       ',
  `first_category_id` int(11) NOT NULL COMMENT '一级类编号（外键）',
  `second_category_name` varchar(20) NOT NULL COMMENT '类别名称',
  PRIMARY KEY (`second_category_id`),
  KEY `first_category_6_idx` (`first_category_id`),
  CONSTRAINT `first_category_6` FOREIGN KEY (`first_category_id`) REFERENCES `tb_first_category` (`first_category_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_second_category`
--

LOCK TABLES `tb_second_category` WRITE;
/*!40000 ALTER TABLE `tb_second_category` DISABLE KEYS */;
INSERT INTO `tb_second_category` VALUES (0,1,'休闲零食'),(1,1,'饼干蛋糕'),(2,23,'软糖'),(3,26,'花言巧语'),(4,26,'糖衣小屋'),(7,26,'卡拉迪克'),(8,26,'暖春童话'),(9,26,'华盛童装批发行'),(11,7,'爱丝蓝内衣厂'),(12,7,'炫点服饰'),(13,7,'雪茵美内衣厂批发'),(14,8,'今生只围你'),(15,8,'忆佳人'),(16,8,'斐洱普斯'),(17,8,'聚百坊'),(18,8,'朵朵棉织直营店'),(19,9,'琳琅鞋业'),(20,9,'宏利鞋业'),(21,9,'比爱靓点鞋业'),(22,9,'浪人怪怪'),(23,52,'B'),(24,52,'C');
/*!40000 ALTER TABLE `tb_second_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user`
--

DROP TABLE IF EXISTS `tb_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号，主键，自动增长',
  `username` varchar(50) NOT NULL COMMENT '登录账号',
  `password` varchar(50) NOT NULL COMMENT '登录密码',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `sex` char(2) NOT NULL DEFAULT '男' COMMENT '性别',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `regtime` datetime DEFAULT NULL COMMENT '注册时间',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间（删）',
  `user_state` int(1) NOT NULL DEFAULT '0' COMMENT '默认为0，黑名单为1',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user`
--

LOCK TABLES `tb_user` WRITE;
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` VALUES (1,'whn','1','13212341234','女','whn@chinasofti.com','2019-05-01 14:20:47',NULL,0),(2,'zky','1','12312312312','男','bigcassiel@126.com','2019-06-26 02:01:27',NULL,0),(3,'qwe','1','12345678912','男',NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-12 10:19:07
