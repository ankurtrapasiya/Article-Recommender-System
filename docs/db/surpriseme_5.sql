-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 26, 2012 at 01:03 AM
-- Server version: 5.5.24
-- PHP Version: 5.3.10-1ubuntu3.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `surpriseme`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_del_article`(
   IN 
  `p_articleid`
   int
)
BEGIN
  DELETE FROM `article`
  WHERE     
    (`articleid` = `p_articleid`);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_del_articleinterest`(
   IN 
  `p_articleid`
   int,
   IN 
  `p_interestid`
   int
)
BEGIN
  DELETE FROM `articleinterest`
  WHERE     
    (`articleid` = `p_articleid`) AND    
    (`interestid` = `p_interestid`);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_del_articlelinks`(
   IN 
  `p_articleid`
   int,
   IN 
  `p_sourceid`
   int
)
BEGIN
  DELETE FROM `articlelinks`
  WHERE     
    (`articleid` = `p_articleid`) AND    
    (`sourceid` = `p_sourceid`);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_del_articletag`(
   IN 
  `p_articleid`
   int,
   IN 
  `p_tagid`
   int
)
BEGIN
  DELETE FROM `articletag`
  WHERE     
    (`articleid` = `p_articleid`) AND    
    (`tagid` = `p_tagid`);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_del_blockedusers`(
   IN 
  `p_userid`
   int,
   IN 
  `p_blockerid`
   int
)
BEGIN
  DELETE FROM `blockedusers`
  WHERE     
    (`userid` = `p_userid`) AND    
    (`blockerid` = `p_blockerid`);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_del_favourites`(
   IN 
  `p_userid`
   int,
   IN 
  `p_articleid`
   int
)
BEGIN
  DELETE FROM `favourites`
  WHERE     
    (`userid` = `p_userid`) AND    
    (`articleid` = `p_articleid`);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_del_interest`(
   IN 
  `p_interestid`
   int
)
BEGIN
  DELETE FROM `interest`
  WHERE     
    (`interestid` = `p_interestid`);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_del_interestsources`(
   IN 
  `p_interestid`
   int,
   IN 
  `p_sourceid`
   int
)
BEGIN
  DELETE FROM `interestsources`
  WHERE     
    (`interestid` = `p_interestid`) AND    
    (`sourceid` = `p_sourceid`);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_del_role`(
   IN 
  `p_rolename`
   varchar(20)
)
BEGIN
  DELETE FROM `role`
  WHERE     
    (`rolename` = `p_rolename`);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_del_source`(
   IN 
  `p_sourceid`
   int
)
BEGIN
  DELETE FROM `source`
  WHERE     
    (`sourceid` = `p_sourceid`);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_del_tag`(
   IN 
  `p_tagid`
   int
)
BEGIN
  DELETE FROM `tag`
  WHERE     
    (`tagid` = `p_tagid`);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_del_taginterest`(
   IN 
  `p_tagid`
   int,
   IN 
  `p_interestid`
   int
)
BEGIN
  DELETE FROM `taginterest`
  WHERE     
    (`tagid` = `p_tagid`) AND    
    (`interestid` = `p_interestid`);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_del_user`(
   IN 
  `p_userid`
   int
)
BEGIN
  DELETE FROM `user`
  WHERE     
    (`userid` = `p_userid`);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_del_useractivation`(
   IN 
  `p_id`
   int
)
BEGIN
  DELETE FROM `useractivation`
  WHERE     
    (`id` = `p_id`);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_del_usergraph`(
   IN 
  `p_friendid`
   int,
   IN 
  `p_userid`
   int
)
BEGIN
  DELETE FROM `usergraph`
  WHERE     
    (`friendid` = `p_friendid`) AND    
    (`userid` = `p_userid`);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_del_userhistory`(
   IN 
  `p_userid`
   int,
   IN 
  `p_articleid`
   int,
   IN 
  `p_timestamp`
   datetime
)
BEGIN
  DELETE FROM `userhistory`
  WHERE     
    (`userid` = `p_userid`) AND    
    (`articleid` = `p_articleid`) AND    
    (`timestamp` = `p_timestamp`);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_del_userinterest`(
   IN 
  `p_userid`
   int,
   IN 
  `p_interestid`
   int
)
BEGIN
  DELETE FROM `userinterest`
  WHERE     
    (`userid` = `p_userid`) AND    
    (`interestid` = `p_interestid`);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_del_userrole`(
   IN 
  `p_rolename`
   varchar(20),
   IN 
  `p_userid`
   int
)
BEGIN
  DELETE FROM `userrole`
  WHERE     
    (`rolename` = `p_rolename`) AND    
    (`userid` = `p_userid`);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_del_usersuggestions`(
   IN 
  `p_id`
   int
)
BEGIN
  DELETE FROM `usersuggestions`
  WHERE     
    (`id` = `p_id`);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ins_article`(
  IN `p_title` varchar(200),
  IN `p_body` varchar(65000),
  IN `p_upvote` int,
  IN `p_downvote` int,
  IN `p_viewed` int,
  IN `p_timestamp` datetime
)
BEGIN
  INSERT INTO `article`
  (
    `title`,
    `body`,
    `upvote`,
    `downvote`,
    `viewed`,
    `timestamp`
  )
  VALUES 
  (
    `p_title`,
    `p_body`,
    `p_upvote`,
    `p_downvote`,
    `p_viewed`,
    `p_timestamp`
  );
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ins_articleinterest`(
  IN `p_articleid` int,
  IN `p_interestid` int
)
BEGIN
  INSERT INTO `articleinterest`
  (
    `articleid`,
    `interestid`
  )
  VALUES 
  (
    `p_articleid`,
    `p_interestid`
  );
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ins_articlelinks`(
  IN `p_articleid` int,
  IN `p_articleurl` varchar(200),
  IN `p_sourceid` int
)
BEGIN
  INSERT INTO `articlelinks`
  (
    `articleid`,
    `articleurl`,
    `sourceid`
  )
  VALUES 
  (
    `p_articleid`,
    `p_articleurl`,
    `p_sourceid`
  );
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ins_articletag`(
  IN `p_articleid` int,
  IN `p_tagid` int,
  IN `p_timestamp` datetime
)
BEGIN
  INSERT INTO `articletag`
  (
    `articleid`,
    `tagid`,
    `timestamp`
  )
  VALUES 
  (
    `p_articleid`,
    `p_tagid`,
    `p_timestamp`
  );
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ins_blockedusers`(
  IN `p_userid` int,
  IN `p_blockerid` int,
  IN `p_timestamp` datetime,
  IN `p_reason` varchar(200),
  IN `p_isactive` tinyint(1)
)
BEGIN
  INSERT INTO `blockedusers`
  (
    `userid`,
    `blockerid`,
    `timestamp`,
    `reason`,
	`isactive`
  )
  VALUES 
  (
    `p_userid`,
    `p_blockerid`,
    `p_timestamp`,
    `p_reason`,
	`p_isactive`
  );
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ins_favourites`(
  IN `p_userid` int,
  IN `p_articleid` int
)
BEGIN
  INSERT INTO `favourites`
  (
    `userid`,
    `articleid`
  )
  VALUES 
  (
    `p_userid`,
    `p_articleid`
  );
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ins_interest`(
  IN `p_name` varchar(50),
  IN `p_description` varchar(500),
  IN `p_iconpath` varchar(200),
  IN `p_timestamp` datetime
)
BEGIN
  INSERT INTO `interest`
  (
    `name`,
    `description`,
    `iconpath`,
    `timestamp`
  )
  VALUES 
  (
    `p_name`,
    `p_description`,
    `p_iconpath`,
    `p_timestamp`
  );
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ins_interestsources`(
  IN `p_interestid` int,
  IN `p_sourceid` int
)
BEGIN
  INSERT INTO `interestsources`
  (
    `interestid`,
    `sourceid`
  )
  VALUES 
  (
    `p_interestid`,
    `p_sourceid`
  );
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ins_role`(
  IN `p_rolename` varchar(20),
  IN `p_description` varchar(200)
)
BEGIN
  INSERT INTO `role`
  (
    `rolename`,
    `description`
  )
  VALUES 
  (
    `p_rolename`,
    `p_description`
  );
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ins_source`(
  IN `p_name` varchar(50),
  IN `p_url` varchar(200),
  IN `p_feedurl` varchar(200),
  IN `p_icon` varchar(200),
  IN `p_isactive` tinyint(1)
)
BEGIN
  INSERT INTO `source`
  (
    `name`,
    `url`,
    `feedurl`,
    `icon`,
    `isactive`
  )
  VALUES 
  (
    `p_name`,
    `p_url`,
    `p_feedurl`,
    `p_icon`,
    `p_isactive`
  );
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ins_tag`(
  IN `p_name` varchar(50),
  IN `p_icon` varchar(200),
  IN `p_description` varchar(500)
)
BEGIN
  INSERT INTO `tag`
  (
    `name`,
    `icon`,
    `description`
  )
  VALUES 
  (
    `p_name`,
    `p_icon`,
    `p_description`
  );
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ins_taginterest`(
  IN `p_tagid` int,
  IN `p_interestid` int
)
BEGIN
  INSERT INTO `taginterest`
  (
    `tagid`,
    `interestid`
  )
  VALUES 
  (
    `p_tagid`,
    `p_interestid`
  );
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ins_user`(
  IN `p_username` varchar(20),
  IN `p_password` varchar(50),
  IN `p_email` varchar(20),
  IN `p_firstname` varchar(20),
  IN `p_lastname` varchar(20),
  IN `p_dob` date,
  IN `p_state` varchar(20),
  IN `p_city` varchar(20),
  IN `p_country` varchar(20),
  IN `p_isactive` tinyint(1),
  IN `p_timeofregistration` datetime
)
BEGIN
  INSERT INTO `user`
  (
    `username`,
    `password`,
    `email`,
    `firstname`,
    `lastname`,
    `dob`,
    `state`,
    `city`,
    `country`,
    `isactive`,
    `timeofregistration`
  )
  VALUES 
  (
    `p_username`,
    `p_password`,
    `p_email`,
    `p_firstname`,
    `p_lastname`,
    `p_dob`,
    `p_state`,
    `p_city`,
    `p_country`,
    `p_isactive`,
    `p_timeofregistration`
  );
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ins_useractivation`(
  IN `p_userid` int,
  IN `p_token` varchar(255),
  IN `p_timestamp` datetime,
  IN `p_isactive` tinyint(1)
)
BEGIN
  INSERT INTO `useractivation`
  (
    `userid`,
    `token`,
    `timestamp`,
    `isactive`
  )
  VALUES 
  (
    `p_userid`,
    `p_token`,
    `p_timestamp`,
    `p_isactive`
  );
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ins_usergraph`(
  IN `p_friendid` int,
  IN `p_userid` int
)
BEGIN
  INSERT INTO `usergraph`
  (
    `friendid`,
    `userid`
  )
  VALUES 
  (
    `p_friendid`,
    `p_userid`
  );
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ins_userhistory`(
  IN `p_userid` int,
  IN `p_articleid` int,
  IN `p_timestamp` datetime,
  IN `p_upvote` tinyint(1),
  IN `p_downvote` tinyint(1)
)
BEGIN
  INSERT INTO `userhistory`
  (
    `userid`,
    `articleid`,
    `timestamp`,
    `upvote`,
    `downvote`
  )
  VALUES 
  (
    `p_userid`,
    `p_articleid`,
    `p_timestamp`,
    `p_upvote`,
    `p_downvote`
  );
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ins_userinterest`(
  IN `p_userid` int,
  IN `p_interestid` int
)
BEGIN
  INSERT INTO `userinterest`
  (
    `userid`,
    `interestid`
  )
  VALUES 
  (
    `p_userid`,
    `p_interestid`
  );
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ins_userrole`(
  IN `p_rolename` varchar(20),
  IN `p_userid` int,
  IN `p_username` varchar(20)
)
BEGIN
  INSERT INTO `userrole`
  (
    `rolename`,
    `userid`,
    `username`
  )
  VALUES 
  (
    `p_rolename`,
    `p_userid`,
    `p_username`
  );
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ins_usersuggestions`(
  IN `p_userid` int,
  IN `p_friendid` int,
  IN `p_articleid` int,
  IN `p_isviewed` tinyint(1),
  IN `p_timestamp` datetime
)
BEGIN
  INSERT INTO `usersuggestions`
  (
    `userid`,
    `friendid`,
    `articleid`,
    `isviewed`,
    `timestamp`
  )
  VALUES 
  (
    `p_userid`,
    `p_friendid`,
    `p_articleid`,
    `p_isviewed`,
    `p_timestamp`
  );
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_sel_article`()
BEGIN
  SELECT
    `articleid`,
    `title`,
    `body`,
    `upvote`,
    `downvote`,
    `viewed`,
    `timestamp`
  FROM `article`;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_sel_articleinterest`()
BEGIN
  SELECT
    `articleid`,
    `interestid`
  FROM `articleinterest`;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_sel_articlelinks`()
BEGIN
  SELECT
    `articleid`,
    `articleurl`,
    `sourceid`
  FROM `articlelinks`;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_sel_articletag`()
BEGIN
  SELECT
    `articleid`,
    `tagid`,
    `timestamp`
  FROM `articletag`;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_sel_blockedusers`()
BEGIN
  SELECT
    `userid`,
    `blockerid`,
    `timestamp`,
    `reason`,
	`isactive`
  FROM `blockedusers`;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_sel_favourites`()
BEGIN
  SELECT
    `userid`,
    `articleid`
  FROM `favourites`;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_sel_interest`()
BEGIN
  SELECT
    `interestid`,
    `name`,
    `description`,
    `iconpath`,
    `timestamp`
  FROM `interest`;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_sel_interestsources`()
BEGIN
  SELECT
    `interestid`,
    `sourceid`
  FROM `interestsources`;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_sel_role`()
BEGIN
  SELECT
    `rolename`,
    `description`
  FROM `role`;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_sel_source`()
BEGIN
  SELECT
    `sourceid`,
    `name`,
    `url`,
    `feedurl`,
    `icon`,
    `isactive`
  FROM `source`;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_sel_tag`()
BEGIN
  SELECT
    `tagid`,
    `name`,
    `icon`,
    `description`
  FROM `tag`;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_sel_taginterest`()
BEGIN
  SELECT
    `tagid`,
    `interestid`
  FROM `taginterest`;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_sel_user`()
BEGIN
  SELECT
    `userid`,
    `username`,
    `password`,
    `email`,
    `firstname`,
    `lastname`,
    `dob`,
    `state`,
    `city`,
    `country`,
    `isactive`,
    `timeofregistration`
  FROM `user`;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_sel_useractivation`()
BEGIN
  SELECT
    `id`,
    `userid`,
    `token`,
    `timestamp`,
    `isactive`
  FROM `useractivation`;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_sel_usergraph`()
BEGIN
  SELECT
    `friendid`,
    `userid`
  FROM `usergraph`;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_sel_userhistory`()
BEGIN
  SELECT
    `userid`,
    `articleid`,
    `timestamp`,
    `upvote`,
    `downvote`
  FROM `userhistory`;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_sel_userinterest`()
BEGIN
  SELECT
    `userid`,
    `interestid`
  FROM `userinterest`;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_sel_userrole`()
BEGIN
  SELECT
    `rolename`,
    `userid`,
    `username`
  FROM `userrole`;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_sel_usersuggestions`()
BEGIN
  SELECT
    `id`,
    `userid`,
    `friendid`,
    `articleid`,
    `isviewed`,
    `timestamp`
  FROM `usersuggestions`;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_upd_article`(
  IN `p_articleid` int,
  IN `p_title` varchar(200),
  IN `p_body` varchar(65000),
  IN `p_upvote` int,
  IN `p_downvote` int,
  IN `p_viewed` int,
  IN `p_timestamp` datetime
)
BEGIN
  UPDATE `article` SET
    `title` = `p_title`,
    `body` = `p_body`,
    `upvote` = `p_upvote`,
    `downvote` = `p_downvote`,
    `viewed` = `p_viewed`,
    `timestamp` = `p_timestamp`
  WHERE 
    (`articleid` = `p_articleid`);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_upd_blockedusers`(
  IN `p_userid` int,
  IN `p_blockerid` int,
  IN `p_timestamp` datetime,
  IN `p_reason` varchar(200),
  IN `p_isactive` tinyint(1)
)
BEGIN
  UPDATE `blockedusers` SET
    `reason` = `p_reason`,
	`isactive` = `p_isactive`
  WHERE 
    (`userid` = `p_userid`) AND
    (`blockerid` = `p_blockerid`) AND
    (`timestamp` = `p_timestamp`);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_upd_interest`(
  IN `p_interestid` int,
  IN `p_name` varchar(50),
  IN `p_description` varchar(500),
  IN `p_iconpath` varchar(200),
  IN `p_timestamp` datetime
)
BEGIN
  UPDATE `interest` SET
    `name` = `p_name`,
    `description` = `p_description`,
    `iconpath` = `p_iconpath`,
    `timestamp` = `p_timestamp`
  WHERE 
    (`interestid` = `p_interestid`);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_upd_role`(
  IN `p_rolename` varchar(20),
  IN `p_description` varchar(200)
)
BEGIN
  UPDATE `role` SET
    `description` = `p_description`
  WHERE 
    (`rolename` = `p_rolename`);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_upd_source`(
  IN `p_sourceid` int,
  IN `p_name` varchar(50),
  IN `p_url` varchar(200),
  IN `p_feedurl` varchar(200),
  IN `p_icon` varchar(200),
  IN `p_isactive` tinyint(1)
)
BEGIN
  UPDATE `source` SET
    `name` = `p_name`,
    `url` = `p_url`,
    `feedurl` = `p_feedurl`,
    `icon` = `p_icon`,
    `isactive` = `p_isactive`
  WHERE 
    (`sourceid` = `p_sourceid`);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_upd_tag`(
  IN `p_tagid` int,
  IN `p_name` varchar(50),
  IN `p_icon` varchar(200),
  IN `p_description` varchar(500)
)
BEGIN
  UPDATE `tag` SET
    `name` = `p_name`,
    `icon` = `p_icon`,
    `description` = `p_description`
  WHERE 
    (`tagid` = `p_tagid`);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_upd_user`(
  IN `p_userid` int,
  IN `p_username` varchar(20),
  IN `p_password` varchar(50),
  IN `p_email` varchar(20),
  IN `p_firstname` varchar(20),
  IN `p_lastname` varchar(20),
  IN `p_dob` date,
  IN `p_state` varchar(20),
  IN `p_city` varchar(20),
  IN `p_country` varchar(20),
  IN `p_isactive` tinyint(1),
  IN `p_timeofregistration` datetime
)
BEGIN
  UPDATE `user` SET
    `username` = `p_username`,
    `password` = `p_password`,
    `email` = `p_email`,
    `firstname` = `p_firstname`,
    `lastname` = `p_lastname`,
    `dob` = `p_dob`,
    `state` = `p_state`,
    `city` = `p_city`,
    `country` = `p_country`,
    `isactive` = `p_isactive`,
    `timeofregistration` = `p_timeofregistration`
  WHERE 
    (`userid` = `p_userid`);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_upd_useractivation`(
  IN `p_id` int,
  IN `p_userid` int,
  IN `p_token` varchar(255),
  IN `p_timestamp` datetime,
  IN `p_isactive` tinyint(1)
)
BEGIN
  UPDATE `useractivation` SET
    `userid` = `p_userid`,
    `token` = `p_token`,
    `timestamp` = `p_timestamp`,
    `isactive` = `p_isactive`
  WHERE 
    (`id` = `p_id`);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_upd_userhistory`(
  IN `p_userid` int,
  IN `p_articleid` int,
  IN `p_timestamp` datetime,
  IN `p_upvote` tinyint(1),
  IN `p_downvote` tinyint(1)
)
BEGIN
  UPDATE `userhistory` SET
    `upvote` = `p_upvote`,
    `downvote` = `p_downvote`
  WHERE 
    (`userid` = `p_userid`) AND
    (`articleid` = `p_articleid`) AND
    (`timestamp` = `p_timestamp`);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_upd_userrole`(
  IN `p_rolename` varchar(20),
  IN `p_userid` int,
  IN `p_username` varchar(20)
)
BEGIN
  UPDATE `userrole` SET
    `username` = `p_username`
  WHERE 
    (`rolename` = `p_rolename`) AND
    (`userid` = `p_userid`);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_upd_usersuggestions`(
  IN `p_id` int,
  IN `p_userid` int,
  IN `p_friendid` int,
  IN `p_articleid` int,
  IN `p_isviewed` tinyint(1),
  IN `p_timestamp` datetime
)
BEGIN
  UPDATE `usersuggestions` SET
    `userid` = `p_userid`,
    `friendid` = `p_friendid`,
    `articleid` = `p_articleid`,
    `isviewed` = `p_isviewed`,
    `timestamp` = `p_timestamp`
  WHERE 
    (`id` = `p_id`);
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `article`
--

CREATE TABLE IF NOT EXISTS `article` (
  `articleid` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `body` varchar(65000) NOT NULL,
  `upvote` int(11) NOT NULL,
  `downvote` int(11) NOT NULL,
  `viewed` int(11) NOT NULL,
  `timestamp` datetime NOT NULL,
  PRIMARY KEY (`articleid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `articleinterest`
--

CREATE TABLE IF NOT EXISTS `articleinterest` (
  `articleid` int(11) NOT NULL,
  `interestid` int(11) NOT NULL,
  PRIMARY KEY (`articleid`,`interestid`),
  KEY `articleid` (`articleid`,`interestid`),
  KEY `articleid_2` (`articleid`),
  KEY `interestid` (`interestid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `articlelinks`
--

CREATE TABLE IF NOT EXISTS `articlelinks` (
  `articleid` int(11) NOT NULL,
  `articleurl` varchar(200) NOT NULL,
  `sourceid` int(11) NOT NULL,
  PRIMARY KEY (`articleid`,`sourceid`),
  KEY `articleid` (`articleid`,`sourceid`),
  KEY `sourceid` (`sourceid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `articletag`
--

CREATE TABLE IF NOT EXISTS `articletag` (
  `articleid` int(11) NOT NULL DEFAULT '0',
  `tagid` int(11) NOT NULL DEFAULT '0',
  `timestamp` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`articleid`,`tagid`,`timestamp`),
  KEY `fk2` (`tagid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `blockedusers`
--

CREATE TABLE IF NOT EXISTS `blockedusers` (
  `userid` int(11) NOT NULL DEFAULT '0',
  `blockerid` int(11) NOT NULL DEFAULT '0',
  `timestamp` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `reason` varchar(200) DEFAULT NULL,
  `isactive` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`userid`,`blockerid`,`timestamp`),
  KEY `u_fk1` (`blockerid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `favourites`
--

CREATE TABLE IF NOT EXISTS `favourites` (
  `userid` int(11) NOT NULL,
  `articleid` int(11) NOT NULL,
  PRIMARY KEY (`userid`,`articleid`),
  KEY `userid` (`userid`,`articleid`),
  KEY `articleid` (`articleid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `interest`
--

CREATE TABLE IF NOT EXISTS `interest` (
  `interestid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(500) NOT NULL,
  `iconpath` varchar(200) NOT NULL,
  `timestamp` datetime NOT NULL,
  PRIMARY KEY (`interestid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `interestsources`
--

CREATE TABLE IF NOT EXISTS `interestsources` (
  `interestid` int(11) NOT NULL,
  `sourceid` int(11) NOT NULL,
  PRIMARY KEY (`interestid`,`sourceid`),
  KEY `interestid` (`interestid`),
  KEY `sourceid` (`sourceid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `rolename` varchar(20) NOT NULL,
  `description` varchar(200) NOT NULL,
  PRIMARY KEY (`rolename`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `source`
--

CREATE TABLE IF NOT EXISTS `source` (
  `sourceid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `url` varchar(200) NOT NULL,
  `feedurl` varchar(200) NOT NULL,
  `icon` varchar(200) NOT NULL,
  `isactive` tinyint(1) NOT NULL,
  PRIMARY KEY (`sourceid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `tag`
--

CREATE TABLE IF NOT EXISTS `tag` (
  `tagid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `icon` varchar(200) NOT NULL,
  `description` varchar(500) NOT NULL,
  PRIMARY KEY (`tagid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `taginterest`
--

CREATE TABLE IF NOT EXISTS `taginterest` (
  `tagid` int(11) NOT NULL,
  `interestid` int(11) NOT NULL,
  PRIMARY KEY (`tagid`,`interestid`),
  KEY `tagid` (`tagid`,`interestid`),
  KEY `interestid` (`interestid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(20) NOT NULL,
  `firstname` varchar(20) NOT NULL,
  `lastname` varchar(20) NOT NULL,
  `dob` date NOT NULL,
  `state` varchar(20) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `country` varchar(20) NOT NULL,
  `isactive` tinyint(1) NOT NULL,
  `timeofregistration` datetime NOT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `username_unq` (`username`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `username_2` (`username`),
  UNIQUE KEY `username_3` (`username`),
  UNIQUE KEY `username_4` (`username`),
  UNIQUE KEY `username_5` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

-- --------------------------------------------------------

--
-- Table structure for table `useractivation`
--

CREATE TABLE IF NOT EXISTS `useractivation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `token` varchar(255) NOT NULL,
  `timestamp` datetime NOT NULL,
  `isactive` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ua_fk` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `usergraph`
--

CREATE TABLE IF NOT EXISTS `usergraph` (
  `friendid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  PRIMARY KEY (`friendid`,`userid`),
  KEY `userid` (`userid`),
  KEY `userid_2` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `userhistory`
--

CREATE TABLE IF NOT EXISTS `userhistory` (
  `userid` int(11) NOT NULL,
  `articleid` int(11) NOT NULL,
  `timestamp` datetime NOT NULL,
  `upvote` tinyint(1) NOT NULL,
  `downvote` tinyint(1) NOT NULL,
  PRIMARY KEY (`userid`,`articleid`,`timestamp`),
  KEY `Userid` (`userid`,`articleid`),
  KEY `Userid_2` (`userid`),
  KEY `articleid` (`articleid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `userinterest`
--

CREATE TABLE IF NOT EXISTS `userinterest` (
  `userid` int(11) NOT NULL DEFAULT '0',
  `interestid` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`userid`,`interestid`),
  KEY `u_i1_fk` (`interestid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `userrole`
--

CREATE TABLE IF NOT EXISTS `userrole` (
  `rolename` varchar(20) NOT NULL,
  `userid` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  PRIMARY KEY (`rolename`,`userid`),
  KEY `rolename` (`rolename`,`userid`),
  KEY `rolename_2` (`rolename`),
  KEY `userid` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `usersuggestions`
--

CREATE TABLE IF NOT EXISTS `usersuggestions` (
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
  KEY `articleid` (`articleid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `articleinterest`
--
ALTER TABLE `articleinterest`
  ADD CONSTRAINT `articleinterest_ibfk_1` FOREIGN KEY (`articleid`) REFERENCES `article` (`articleid`),
  ADD CONSTRAINT `articleinterest_ibfk_2` FOREIGN KEY (`interestid`) REFERENCES `interest` (`interestid`);

--
-- Constraints for table `articlelinks`
--
ALTER TABLE `articlelinks`
  ADD CONSTRAINT `articlelinks_ibfk_1` FOREIGN KEY (`articleid`) REFERENCES `article` (`articleid`),
  ADD CONSTRAINT `articlelinks_ibfk_2` FOREIGN KEY (`sourceid`) REFERENCES `source` (`sourceid`);

--
-- Constraints for table `articletag`
--
ALTER TABLE `articletag`
  ADD CONSTRAINT `fk1` FOREIGN KEY (`articleid`) REFERENCES `article` (`articleid`),
  ADD CONSTRAINT `fk2` FOREIGN KEY (`tagid`) REFERENCES `tag` (`tagid`);

--
-- Constraints for table `blockedusers`
--
ALTER TABLE `blockedusers`
  ADD CONSTRAINT `u_fk` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`),
  ADD CONSTRAINT `u_fk1` FOREIGN KEY (`blockerid`) REFERENCES `user` (`userid`);

--
-- Constraints for table `favourites`
--
ALTER TABLE `favourites`
  ADD CONSTRAINT `favourites_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`),
  ADD CONSTRAINT `favourites_ibfk_2` FOREIGN KEY (`articleid`) REFERENCES `article` (`articleid`);

--
-- Constraints for table `interestsources`
--
ALTER TABLE `interestsources`
  ADD CONSTRAINT `interestsources_ibfk_1` FOREIGN KEY (`interestid`) REFERENCES `interest` (`interestid`),
  ADD CONSTRAINT `interestsources_ibfk_2` FOREIGN KEY (`sourceid`) REFERENCES `source` (`sourceid`);

--
-- Constraints for table `taginterest`
--
ALTER TABLE `taginterest`
  ADD CONSTRAINT `taginterest_ibfk_1` FOREIGN KEY (`tagid`) REFERENCES `tag` (`tagid`),
  ADD CONSTRAINT `taginterest_ibfk_2` FOREIGN KEY (`interestid`) REFERENCES `interest` (`interestid`);

--
-- Constraints for table `useractivation`
--
ALTER TABLE `useractivation`
  ADD CONSTRAINT `ua_fk` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`);

--
-- Constraints for table `usergraph`
--
ALTER TABLE `usergraph`
  ADD CONSTRAINT `usergraph_ibfk_1` FOREIGN KEY (`friendid`) REFERENCES `user` (`userid`),
  ADD CONSTRAINT `usergraph_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`);

--
-- Constraints for table `userhistory`
--
ALTER TABLE `userhistory`
  ADD CONSTRAINT `userhistory_ibfk_1` FOREIGN KEY (`Userid`) REFERENCES `user` (`userid`),
  ADD CONSTRAINT `userhistory_ibfk_2` FOREIGN KEY (`articleid`) REFERENCES `article` (`articleid`);

--
-- Constraints for table `userinterest`
--
ALTER TABLE `userinterest`
  ADD CONSTRAINT `u_i1_fk` FOREIGN KEY (`interestid`) REFERENCES `interest` (`interestid`),
  ADD CONSTRAINT `u_i_fk` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`);

--
-- Constraints for table `userrole`
--
ALTER TABLE `userrole`
  ADD CONSTRAINT `userrole_ibfk_1` FOREIGN KEY (`rolename`) REFERENCES `role` (`rolename`),
  ADD CONSTRAINT `userrole_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`);

--
-- Constraints for table `usersuggestions`
--
ALTER TABLE `usersuggestions`
  ADD CONSTRAINT `usersuggestions_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`),
  ADD CONSTRAINT `usersuggestions_ibfk_2` FOREIGN KEY (`friendid`) REFERENCES `usergraph` (`friendid`),
  ADD CONSTRAINT `usersuggestions_ibfk_3` FOREIGN KEY (`articleid`) REFERENCES `article` (`articleid`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
