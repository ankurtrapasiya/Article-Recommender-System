-- MySQL dump 10.13  Distrib 5.5.24, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: surpriseme
-- ------------------------------------------------------
-- Server version	5.5.24-0ubuntu0.12.04.1-log

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
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article` (
  `articleid` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `body` varchar(65000) NOT NULL,
  `upvote` int(11) NOT NULL,
  `downvote` int(11) NOT NULL,
  `viewed` int(11) NOT NULL,
  `timestamp` datetime NOT NULL,
  PRIMARY KEY (`articleid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `articleinterest`
--

DROP TABLE IF EXISTS `articleinterest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `articleinterest` (
  `articleid` int(11) NOT NULL,
  `interestid` int(11) NOT NULL,
  PRIMARY KEY (`articleid`,`interestid`),
  KEY `articleid` (`articleid`,`interestid`),
  KEY `articleid_2` (`articleid`),
  KEY `interestid` (`interestid`),
  CONSTRAINT `articleinterest_ibfk_1` FOREIGN KEY (`articleid`) REFERENCES `article` (`articleid`),
  CONSTRAINT `articleinterest_ibfk_2` FOREIGN KEY (`interestid`) REFERENCES `interest` (`interestid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articleinterest`
--

LOCK TABLES `articleinterest` WRITE;
/*!40000 ALTER TABLE `articleinterest` DISABLE KEYS */;
/*!40000 ALTER TABLE `articleinterest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `articlelinks`
--

DROP TABLE IF EXISTS `articlelinks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `articlelinks` (
  `articleid` int(11) NOT NULL,
  `articleurl` varchar(200) NOT NULL,
  `sourceid` int(11) NOT NULL,
  PRIMARY KEY (`articleid`,`sourceid`),
  KEY `articleid` (`articleid`,`sourceid`),
  KEY `sourceid` (`sourceid`),
  CONSTRAINT `articlelinks_ibfk_1` FOREIGN KEY (`articleid`) REFERENCES `article` (`articleid`),
  CONSTRAINT `articlelinks_ibfk_2` FOREIGN KEY (`sourceid`) REFERENCES `source` (`sourceid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articlelinks`
--

LOCK TABLES `articlelinks` WRITE;
/*!40000 ALTER TABLE `articlelinks` DISABLE KEYS */;
/*!40000 ALTER TABLE `articlelinks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `articletag`
--

DROP TABLE IF EXISTS `articletag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `articletag` (
  `articleid` int(11) NOT NULL DEFAULT '0',
  `tagid` int(11) NOT NULL DEFAULT '0',
  `timestamp` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`articleid`,`tagid`,`timestamp`),
  KEY `fk2` (`tagid`),
  CONSTRAINT `fk1` FOREIGN KEY (`articleid`) REFERENCES `article` (`articleid`),
  CONSTRAINT `fk2` FOREIGN KEY (`tagid`) REFERENCES `tag` (`tagid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articletag`
--

LOCK TABLES `articletag` WRITE;
/*!40000 ALTER TABLE `articletag` DISABLE KEYS */;
/*!40000 ALTER TABLE `articletag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blockedusers`
--

DROP TABLE IF EXISTS `blockedusers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `blockedusers` (
  `userid` int(11) NOT NULL DEFAULT '0',
  `blockerid` int(11) NOT NULL DEFAULT '0',
  `timestamp` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `reason` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`userid`,`blockerid`,`timestamp`),
  KEY `u_fk1` (`blockerid`),
  CONSTRAINT `u_fk` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`),
  CONSTRAINT `u_fk1` FOREIGN KEY (`blockerid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blockedusers`
--

LOCK TABLES `blockedusers` WRITE;
/*!40000 ALTER TABLE `blockedusers` DISABLE KEYS */;
/*!40000 ALTER TABLE `blockedusers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favourites`
--

DROP TABLE IF EXISTS `favourites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favourites` (
  `userid` int(11) NOT NULL,
  `articleid` int(11) NOT NULL,
  PRIMARY KEY (`userid`,`articleid`),
  KEY `userid` (`userid`,`articleid`),
  KEY `articleid` (`articleid`),
  CONSTRAINT `favourites_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`),
  CONSTRAINT `favourites_ibfk_2` FOREIGN KEY (`articleid`) REFERENCES `article` (`articleid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favourites`
--

LOCK TABLES `favourites` WRITE;
/*!40000 ALTER TABLE `favourites` DISABLE KEYS */;
/*!40000 ALTER TABLE `favourites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interest`
--

DROP TABLE IF EXISTS `interest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `interest` (
  `interestid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(500) NOT NULL,
  `iconpath` varchar(200) NOT NULL,
  `timestamp` datetime NOT NULL,
  PRIMARY KEY (`interestid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interest`
--

LOCK TABLES `interest` WRITE;
/*!40000 ALTER TABLE `interest` DISABLE KEYS */;
/*!40000 ALTER TABLE `interest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interestsources`
--

DROP TABLE IF EXISTS `interestsources`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `interestsources` (
  `interestid` int(11) NOT NULL,
  `sourceid` int(11) NOT NULL,
  PRIMARY KEY (`interestid`,`sourceid`),
  KEY `interestid` (`interestid`),
  KEY `sourceid` (`sourceid`),
  CONSTRAINT `interestsources_ibfk_1` FOREIGN KEY (`interestid`) REFERENCES `interest` (`interestid`),
  CONSTRAINT `interestsources_ibfk_2` FOREIGN KEY (`sourceid`) REFERENCES `source` (`sourceid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interestsources`
--

LOCK TABLES `interestsources` WRITE;
/*!40000 ALTER TABLE `interestsources` DISABLE KEYS */;
/*!40000 ALTER TABLE `interestsources` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `rolename` varchar(20) NOT NULL,
  `description` varchar(200) NOT NULL,
  PRIMARY KEY (`rolename`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `source`
--

DROP TABLE IF EXISTS `source`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `source` (
  `sourceid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `url` varchar(200) NOT NULL,
  `feedurl` varchar(200) NOT NULL,
  `icon` varchar(200) NOT NULL,
  `isactive` tinyint(1) NOT NULL,
  PRIMARY KEY (`sourceid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `source`
--

LOCK TABLES `source` WRITE;
/*!40000 ALTER TABLE `source` DISABLE KEYS */;
/*!40000 ALTER TABLE `source` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tag` (
  `tagid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `icon` varchar(200) NOT NULL,
  `description` varchar(500) NOT NULL,
  PRIMARY KEY (`tagid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taginterest`
--

DROP TABLE IF EXISTS `taginterest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `taginterest` (
  `tagid` int(11) NOT NULL,
  `interestid` int(11) NOT NULL,
  PRIMARY KEY (`tagid`,`interestid`),
  KEY `tagid` (`tagid`,`interestid`),
  KEY `interestid` (`interestid`),
  CONSTRAINT `taginterest_ibfk_1` FOREIGN KEY (`tagid`) REFERENCES `tag` (`tagid`),
  CONSTRAINT `taginterest_ibfk_2` FOREIGN KEY (`interestid`) REFERENCES `interest` (`interestid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taginterest`
--

LOCK TABLES `taginterest` WRITE;
/*!40000 ALTER TABLE `taginterest` DISABLE KEYS */;
/*!40000 ALTER TABLE `taginterest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(20) NOT NULL,
  `firstname` varchar(20) NOT NULL,
  `lastname` varchar(20) NOT NULL,
  `dob` date NOT NULL,
  `state` varchar(20) NOT NULL,
  `city` varchar(20) NOT NULL,
  `country` varchar(20) NOT NULL,
  `isactive` tinyint(1) NOT NULL,
  `timeofregistration` datetime NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useractivation`
--

DROP TABLE IF EXISTS `useractivation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useractivation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `token` varchar(255) NOT NULL,
  `timestamp` datetime NOT NULL,
  `isactive` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ua_fk` (`userid`),
  CONSTRAINT `ua_fk` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useractivation`
--

LOCK TABLES `useractivation` WRITE;
/*!40000 ALTER TABLE `useractivation` DISABLE KEYS */;
/*!40000 ALTER TABLE `useractivation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usergraph`
--

DROP TABLE IF EXISTS `usergraph`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usergraph` (
  `friendid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  PRIMARY KEY (`friendid`,`userid`),
  KEY `userid` (`userid`),
  KEY `userid_2` (`userid`),
  CONSTRAINT `usergraph_ibfk_1` FOREIGN KEY (`friendid`) REFERENCES `user` (`userid`),
  CONSTRAINT `usergraph_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usergraph`
--

LOCK TABLES `usergraph` WRITE;
/*!40000 ALTER TABLE `usergraph` DISABLE KEYS */;
/*!40000 ALTER TABLE `usergraph` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userhistory`
--

DROP TABLE IF EXISTS `userhistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userhistory` (
  `Userid` int(11) NOT NULL,
  `articleid` int(11) NOT NULL,
  `timestamp` datetime NOT NULL,
  `upvote` tinyint(1) NOT NULL,
  `downvote` tinyint(1) NOT NULL,
  PRIMARY KEY (`Userid`,`articleid`,`timestamp`),
  KEY `Userid` (`Userid`,`articleid`),
  KEY `Userid_2` (`Userid`),
  KEY `articleid` (`articleid`),
  CONSTRAINT `userhistory_ibfk_1` FOREIGN KEY (`Userid`) REFERENCES `user` (`userid`),
  CONSTRAINT `userhistory_ibfk_2` FOREIGN KEY (`articleid`) REFERENCES `article` (`articleid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userhistory`
--

LOCK TABLES `userhistory` WRITE;
/*!40000 ALTER TABLE `userhistory` DISABLE KEYS */;
/*!40000 ALTER TABLE `userhistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userinterest`
--

DROP TABLE IF EXISTS `userinterest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userinterest` (
  `userid` int(11) NOT NULL DEFAULT '0',
  `interestid` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`userid`,`interestid`),
  KEY `u_i1_fk` (`interestid`),
  CONSTRAINT `u_i1_fk` FOREIGN KEY (`interestid`) REFERENCES `interest` (`interestid`),
  CONSTRAINT `u_i_fk` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userinterest`
--

LOCK TABLES `userinterest` WRITE;
/*!40000 ALTER TABLE `userinterest` DISABLE KEYS */;
/*!40000 ALTER TABLE `userinterest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userrole`
--

DROP TABLE IF EXISTS `userrole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userrole` (
  `rolename` varchar(20) NOT NULL,
  `userid` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  PRIMARY KEY (`rolename`,`userid`),
  KEY `rolename` (`rolename`,`userid`),
  KEY `rolename_2` (`rolename`),
  KEY `userid` (`userid`),
  CONSTRAINT `userrole_ibfk_1` FOREIGN KEY (`rolename`) REFERENCES `role` (`rolename`),
  CONSTRAINT `userrole_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userrole`
--

LOCK TABLES `userrole` WRITE;
/*!40000 ALTER TABLE `userrole` DISABLE KEYS */;
/*!40000 ALTER TABLE `userrole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usersuggestions`
--

DROP TABLE IF EXISTS `usersuggestions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usersuggestions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `friendid` int(11) NOT NULL,
  `articleid` int(11) NOT NULL,
  `isviewed` tinyint(1) NOT NULL,
  `timestamp` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userid` (`userid`,`friendid`,`articleid`),
  KEY `userid_2` (`userid`),
  KEY `friendid` (`friendid`),
  KEY `articleid` (`articleid`),
  CONSTRAINT `usersuggestions_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`),
  CONSTRAINT `usersuggestions_ibfk_2` FOREIGN KEY (`friendid`) REFERENCES `usergraph` (`friendid`),
  CONSTRAINT `usersuggestions_ibfk_3` FOREIGN KEY (`articleid`) REFERENCES `article` (`articleid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usersuggestions`
--

LOCK TABLES `usersuggestions` WRITE;
/*!40000 ALTER TABLE `usersuggestions` DISABLE KEYS */;
/*!40000 ALTER TABLE `usersuggestions` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-10-13 23:43:42
