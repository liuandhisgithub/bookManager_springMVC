/*
Navicat MySQL Data Transfer

Source Server         : liu
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : bookmanager

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2020-05-09 19:10:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL,
  `password` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('20170001', '111111');

-- ----------------------------
-- Table structure for book_info
-- ----------------------------
DROP TABLE IF EXISTS `book_info`;
CREATE TABLE `book_info` (
  `book_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `author` varchar(50) NOT NULL,
  `publish` varchar(30) NOT NULL,
  `ISBN` varchar(13) NOT NULL,
  `introduction` text,
  `language` varchar(10) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `pubdate` date DEFAULT NULL,
  `class_id` int(11) DEFAULT NULL,
  `like_num` int(11) DEFAULT '0',
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=50000017 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book_info
-- ----------------------------
INSERT INTO `book_info` VALUES ('1', '悲惨世界', '雨果', '译林出版社', '3238109382913', '悲惨世界简介', '中文', '22.00', '2001-01-01', '2', '0');
INSERT INTO `book_info` VALUES ('50000015', '红楼梦', '曹雪芹', '中华书局', '9787101086591', '红楼梦简介', '中文', '78.00', '2012-05-01', '9', '1');
INSERT INTO `book_info` VALUES ('50000016', '西游记', '吴承恩', '人民文学出版社', '9787101086591', '西游记简介', '中文', '78.00', '2012-05-01', '9', '0');

-- ----------------------------
-- Table structure for class_info
-- ----------------------------
DROP TABLE IF EXISTS `class_info`;
CREATE TABLE `class_info` (
  `class_id` int(11) NOT NULL,
  `class_name` varchar(45) NOT NULL,
  PRIMARY KEY (`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of class_info
-- ----------------------------
INSERT INTO `class_info` VALUES ('1', '马克思主义');
INSERT INTO `class_info` VALUES ('2', '哲学');
INSERT INTO `class_info` VALUES ('3', '社会科学总论');
INSERT INTO `class_info` VALUES ('4', '政治法律');
INSERT INTO `class_info` VALUES ('5', '军事');
INSERT INTO `class_info` VALUES ('6', '经济');
INSERT INTO `class_info` VALUES ('7', '文化');
INSERT INTO `class_info` VALUES ('8', '语言');
INSERT INTO `class_info` VALUES ('9', '文学');
INSERT INTO `class_info` VALUES ('10', '艺术');
INSERT INTO `class_info` VALUES ('11', '历史地理');
INSERT INTO `class_info` VALUES ('12', '自然科学总论');
INSERT INTO `class_info` VALUES ('13', ' 数理科学和化学');
INSERT INTO `class_info` VALUES ('14', '天文学、地球科学');
INSERT INTO `class_info` VALUES ('15', '生物科学');
INSERT INTO `class_info` VALUES ('16', '医药、卫生');
INSERT INTO `class_info` VALUES ('17', '农业科学');
INSERT INTO `class_info` VALUES ('18', '工业技术');
INSERT INTO `class_info` VALUES ('19', '交通运输');
INSERT INTO `class_info` VALUES ('20', '航空、航天');
INSERT INTO `class_info` VALUES ('21', '环境科学');
INSERT INTO `class_info` VALUES ('22', '综合');

-- ----------------------------
-- Table structure for lend_list
-- ----------------------------
DROP TABLE IF EXISTS `lend_list`;
CREATE TABLE `lend_list` (
  `sernum` bigint(20) NOT NULL AUTO_INCREMENT,
  `book_id` bigint(20) NOT NULL,
  `reader_id` int(11) NOT NULL,
  `lend_date` date DEFAULT NULL,
  `back_date` date DEFAULT NULL,
  PRIMARY KEY (`sernum`)
) ENGINE=InnoDB AUTO_INCREMENT=2015040146 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lend_list
-- ----------------------------
INSERT INTO `lend_list` VALUES ('2015040139', '10000001', '1501014101', '2017-03-15', '2017-06-16');
INSERT INTO `lend_list` VALUES ('2015040140', '10000003', '1501014101', '2017-06-10', '2017-09-02');
INSERT INTO `lend_list` VALUES ('2015040141', '10000006', '1501014101', '2017-06-12', '2017-09-02');
INSERT INTO `lend_list` VALUES ('2015040142', '50000004', '1501014101', '2017-03-15', '2017-09-03');
INSERT INTO `lend_list` VALUES ('2015040143', '50000005', '1501014103', '2017-06-15', null);
INSERT INTO `lend_list` VALUES ('2015040144', '50000010', '1501014104', '2017-06-15', null);
INSERT INTO `lend_list` VALUES ('2015040145', '10000001', '1501014101', '2017-09-02', '2017-09-02');

-- ----------------------------
-- Table structure for like_book
-- ----------------------------
DROP TABLE IF EXISTS `like_book`;
CREATE TABLE `like_book` (
  `reader_id` int(11) NOT NULL,
  `book_id` bigint(20) NOT NULL,
  `class_id` int(11) unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`reader_id`,`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of like_book
-- ----------------------------
INSERT INTO `like_book` VALUES ('1501014101', '50000015', '00000000009');

-- ----------------------------
-- Table structure for like_reflection
-- ----------------------------
DROP TABLE IF EXISTS `like_reflection`;
CREATE TABLE `like_reflection` (
  `reader_id` int(11) NOT NULL,
  `reflection_id` bigint(20) NOT NULL,
  `class_id` int(11) unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`reader_id`,`reflection_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of like_reflection
-- ----------------------------
INSERT INTO `like_reflection` VALUES ('1501014101', '1', '00000000009');

-- ----------------------------
-- Table structure for reader_card
-- ----------------------------
DROP TABLE IF EXISTS `reader_card`;
CREATE TABLE `reader_card` (
  `reader_id` int(11) NOT NULL,
  `name` varchar(16) NOT NULL,
  `passwd` varchar(15) NOT NULL DEFAULT '111111',
  `card_state` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`reader_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reader_card
-- ----------------------------
INSERT INTO `reader_card` VALUES ('1501014101', '张华', '111111', '1');
INSERT INTO `reader_card` VALUES ('1501014102', '王小伟', '111111', '1');
INSERT INTO `reader_card` VALUES ('1501014103', '王莞尔', '111111', '1');
INSERT INTO `reader_card` VALUES ('1501014104', '张明华', '111111', '1');
INSERT INTO `reader_card` VALUES ('1501014105', '李一琛', '111111', '1');
INSERT INTO `reader_card` VALUES ('1501014106', '李二飞', '123456', '1');
INSERT INTO `reader_card` VALUES ('1634110129', '111', '111111', '1');

-- ----------------------------
-- Table structure for reader_info
-- ----------------------------
DROP TABLE IF EXISTS `reader_info`;
CREATE TABLE `reader_info` (
  `reader_id` int(11) NOT NULL,
  `name` varchar(16) NOT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `birth` date DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `telcode` varchar(11) NOT NULL,
  PRIMARY KEY (`reader_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reader_info
-- ----------------------------
INSERT INTO `reader_info` VALUES ('1501014101', '张华', '男', '1620-01-01', '郑州', '2192912923');
INSERT INTO `reader_info` VALUES ('1501014102', '王小伟', '男', '1996-02-01', '北京市', '12345678909');
INSERT INTO `reader_info` VALUES ('1501014103', '王莞尔', '女', '1995-04-15', '浙江省杭州市', '12345678908');
INSERT INTO `reader_info` VALUES ('1501014104', '张明华', '男', '1996-08-29', '陕西省西安市', '12345678907');
INSERT INTO `reader_info` VALUES ('1501014105', '李一琛', '男', '1996-01-01', '陕西省西安市', '15123659875');
INSERT INTO `reader_info` VALUES ('1501014106', '李二飞', '男', '1996-05-03', '山东省青岛市', '15369874123');
INSERT INTO `reader_info` VALUES ('1634110129', '111', '男', '1111-01-01', '1111', '111111111');

-- ----------------------------
-- Table structure for reflection_info
-- ----------------------------
DROP TABLE IF EXISTS `reflection_info`;
CREATE TABLE `reflection_info` (
  `reflection_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `book_name` varchar(50) NOT NULL,
  `reader_id` int(11) NOT NULL,
  `introduction` text NOT NULL,
  `like_num` int(11) DEFAULT '0',
  `class_id` int(11) unsigned zerofill NOT NULL DEFAULT '00000000000',
  PRIMARY KEY (`reflection_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000005 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reflection_info
-- ----------------------------
INSERT INTO `reflection_info` VALUES ('1', '读红楼梦有感', '红楼梦', '1', '红楼梦读后感', '2', '00000000009');
INSERT INTO `reflection_info` VALUES ('10000002', '无题', '红楼梦', '1634110129', '红楼梦写的太好了，我太喜欢了', '0', '00000000009');
INSERT INTO `reflection_info` VALUES ('10000003', '我的西游记感悟', '西游记', '1634110129', '西游记太有趣了，我太喜欢西游记了', '0', '00000000009');
INSERT INTO `reflection_info` VALUES ('10000004', '无题', '悲惨世界', '1634110129', '悲惨世界写的真好', '0', '00000000002');
