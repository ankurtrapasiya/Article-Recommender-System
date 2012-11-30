-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 30, 2012 at 07:01 PM
-- Server version: 5.5.28
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `getSearchArticles`(IN keyword varchar(20))
BEGIN
	select article.articleid, article.title, substring(article.body,1,100) as body, article.upvote, article.downvote, article.viewed from article where getRelevance(wordcount(concat(title, ' ', body),keyword),keyword) > 0 order by getRelevance(wordcount(concat(title, ' ', body),keyword),keyword) desc, getPopularity(upvote, downvote, datediff(date(now()),article.timestamp),viewed) desc;
END$$

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
   int
)
BEGIN
  DELETE FROM `userhistory`
  WHERE     
    (`userid` = `p_userid`) AND    
    (`articleid` = `p_articleid`);
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
  IN `p_timestamp` datetime,
  IN `p_popularityscore` float,
  IN `p_publicationdate` timestamp
)
BEGIN
  INSERT INTO `article`
  (
    `title`,
    `body`,
    `upvote`,
    `downvote`,
    `viewed`,
    `timestamp`,
	`popularityscore`,
	`publicationdate`
  )
  VALUES 
  (
    `p_title`,
    `p_body`,
    `p_upvote`,
    `p_downvote`,
    `p_viewed`,
    `p_timestamp`,
	`p_popularityscore`,
	`p_publicationdate`
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
  IN `p_articleid` int,
	IN `p_readlater` boolean,
	IN `p_isfav` boolean
)
BEGIN
  INSERT INTO `favourites`
  (
    `userid`,
    `articleid`,
	`readlater`,
	`isfav`
  )
  VALUES 
  (
    `p_userid`,
    `p_articleid`,
	`p_readlater`,
	`p_isfav`
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
  IN `p_email` varchar(50),
  IN `p_firstname` varchar(20),
  IN `p_lastname` varchar(20),
  IN `p_dob` date,
  IN `p_state` int,
  IN `p_city` int,
  IN `p_country` int,
  IN `p_isactive` tinyint(1),
  IN `p_timeofregistration` datetime,
  IN `p_image` varchar(255)
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
    `timeofregistration`,
	`image`
  )
  VALUES 
  (
    `p_username`,
    md5(`p_password`),
    `p_email`,
    `p_firstname`,
    `p_lastname`,
    `p_dob`,
    `p_state`,
    `p_city`,
    `p_country`,
    `p_isactive`,
    `p_timeofregistration`,
	`p_image`
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
  IN `p_userid` int,
IN `p_isnotified` bool
)
BEGIN
  INSERT INTO `usergraph`
  (
    `friendid`,
    `userid`,
	`isnotified`
  )
  VALUES 
  (
    `p_friendid`,
    `p_userid`,
	`p_isnotified`
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
    `timestamp`,
	`popularityscore`,
	`publicationdate`
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_sel_article_tag`(IN `id` INT)
select t.tagid,t.name,t.icon,t.description 
from tag t,articletag a
where a.articleid=id
and a.tagid=t.tagid$$

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
    `articleid`,
	`readlater`,
	`isfav`
  FROM `favourites`;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_sel_getarticleid`(IN `id` INT)
select articleid from articletag where tagid=id$$

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

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_sel_remaining`(IN `id` INT)
select tagid,name,icon,description from 
tag where tagid not in(select tagid from articletag where articleid=id)$$

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
    `timeofregistration`,
	`image`
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_sel_useractivationbytoken`(IN tk varchar(50))
begin
 select id from useractivation where token = tk;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_sel_usergraph`()
BEGIN
  SELECT
    `friendid`,
    `userid`,
	`isnotified`
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
  IN `p_timestamp` datetime,
  IN `p_popularityscore` float,
  IN `p_publicationdate` timestamp
)
BEGIN
  UPDATE `article` SET
    `title` = `p_title`,
    `body` = `p_body`,
    `upvote` = `p_upvote`,
    `downvote` = `p_downvote`,
    `viewed` = `p_viewed`,
    `timestamp` = `p_timestamp`,
	`popularityscore` = `p_popularityscore`,
	`publicationdate` =`p_publicationdate`
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
	`isactive` = `p_isactive`,
    `timestamp` = `p_timestamp`
  WHERE 
    (`userid` = `p_userid`) AND
    (`blockerid` = `p_blockerid`);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_upd_favourites`(
IN `p_userid` integer,
IN `p_articleid` integer,
in `p_readlater` boolean,
in `p_isfav` boolean
)
BEGIN
update `favourites`
set `readlater`=`p_readlater`,
`isfav`=`p_isfav`
where (userid=`p_userid`
and articleid=`p_articleid`);
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
  IN `p_email` varchar(50),
  IN `p_firstname` varchar(20),
  IN `p_lastname` varchar(20),
  IN `p_dob` date,
  IN `p_state` int,
  IN `p_city` int,
  IN `p_country` int,
  IN `p_isactive` tinyint(1),
  IN `p_timeofregistration` datetime,
  IN `p_image` varchar(255)
)
BEGIN
  UPDATE `user` SET
    `username` = `p_username`,
    `password` = md5(`p_password`),
    `email` = `p_email`,
    `firstname` = `p_firstname`,
    `lastname` = `p_lastname`,
    `dob` = `p_dob`,
    `state` = `p_state`,
    `city` = `p_city`,
    `country` = `p_country`,
    `isactive` = `p_isactive`,
    `timeofregistration` = `p_timeofregistration`,
	`image` = `p_image`
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_upd_usergraph`(
  IN `p_friendid` int,
  IN `p_userid` int,
  IN `p_isnotifitied` bool
  
)
BEGIN
  UPDATE `usergraph` SET
    `isnotifitied` = `p_isnotifitied`
  WHERE 
    (`userid` = `p_userid`) AND
    (`friendid` = `p_friendid`);
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
    (`articleid` = `p_articleid`);
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

--
-- Functions
--
CREATE DEFINER=`root`@`localhost` FUNCTION `getPopularity`(upvotes INT, downvotes INT, days INT, views INT) RETURNS float
    READS SQL DATA
    DETERMINISTIC
BEGIN
	declare rank float;
	set rank = (upvotes - downvotes - (0.5 * days))/views;
	return rank;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `getRelevance`(wordcount int, keyword varchar(30)) RETURNS float
    READS SQL DATA
    DETERMINISTIC
begin
	declare relevance float;
	declare N int;
	declare nk int;
	declare wildcardkey varchar(30);
	select count(articleid) into N from article;
	set wildcardkey = concat('%',keyword,'%');
	select count(articleid) into nk from article where title like wildcardkey or body like wildcardkey;
	set relevance = wordcount * log10(N/nk);
	return relevance;

end$$

CREATE DEFINER=`root`@`localhost` FUNCTION `wordcount`(title varchar(255), keyword varchar(50)) RETURNS int(11)
    READS SQL DATA
    DETERMINISTIC
begin
declare idx int;
declare wc int;
declare str text;
declare len int;
declare startindex int;
declare searchrange int;
declare flag int default 0;

set str = title;
set wc = 0;

while flag <> 1 do
	set idx = locate(keyword, str);
	if idx <> 0 then
		set wc = wc + 1;
		set len = length(keyword);
		set startindex = idx + len;
		set searchrange = length(str) - startindex;
		set str = substring(str, startindex, searchrange);
	else
		set flag = 1;
	end if;
end while;
return wc;
end$$

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
  `viewed` int(11) NOT NULL DEFAULT '1',
  `timestamp` datetime NOT NULL,
  `popularityscore` float DEFAULT NULL,
  `publicationdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`articleid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=207 ;

--
-- Dumping data for table `article`
--

INSERT INTO `article` (`articleid`, `title`, `body`, `upvote`, `downvote`, `viewed`, `timestamp`, `popularityscore`, `publicationdate`) VALUES
(2, 'ICICI Bank Q2 net profit up 30%, beats forecast', 'MUMBAI: India''s No. 2 lender ICICI Bank Ltd posted a forecast-beating 30% rise in second quarter net profit, bolstered by strong loan growth and higher fee-based income, it said on Friday. ', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(3, 'Computer programming', 'Computer programming (often shortened to programming or coding) is the process of designing, writing, testing, debugging, and maintaining the source code of computer programs. This source code is written in one or more programming languages (such as Java, C++, C#, Python, etc.). The purpose of programming is to create a set of instructions that computers use to perform specific operations or to exhibit desired behaviors. The process of writing source code often requires expertise in many different subjects, including knowledge of the application domain, specialized algorithms and formal logic.', 11, 0, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:39'),
(4, 'DOS to Winows 8: How Microsoft systems have changed from text to touch', 'NEW YORK: With Friday''s release of the touch-centric Windows 8 software, Microsoft continues more than three decades of making operating systems for personal computers.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(5, 'sql article', 'A design pattern in architecture and computer science is a formal way of documenting a solution to a design problem in a particular field of expertise. The idea was introduced by the architect Christopher Alexander in the field of architecture[1] and has been adapted for various other disciplines, including computer science.[2] An organized collection of design patterns that relate to a particular field is called a pattern language.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(6, 'programming1', 'programming1', 11, 0, 1, '2012-11-25 08:20:16', 11, '2012-11-25 02:51:43'),
(7, 'programming2', 'programming2', 11, 0, 1, '2012-11-25 08:20:16', 11, '2012-11-25 02:51:04'),
(8, 'programming3', 'programming3', 11, 0, 1, '2012-11-25 08:20:16', 11, '2012-11-25 04:26:24'),
(9, 'programming4', 'programming4', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(21, 'iPad Mini Display  eUnder The Microscope: Not As Good As iPad 4th Gen, But Much Better Than iPad 2', 'The iPad mini may have a display that''s being singled out in most reviews as below Apple''s recent standards, but a look under the microscope by Repair Labs affirms what I''ve been noticing in person: while the mini definitely doesn''t offer the same kind of quality as an iPad with Retina Display (3rd or 4th gen), its screen is a big step up from the 2nd generation iPad.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(22, 'iOS 6.0.1 Alreadyd Accounts For 7.5% Of iOS Traffic 24 Hours After Release', 'Apple''s minordd bug-zapping update for iOS 6, iOS 6.0.1, is already making up 7.5 percent of mobile web traffic as measured by ad network and analytics firm Chitika, just 24 hours after release. If you''ve been following, you''ll note that that''s just half of the 15 percent adoption rate iOS 6 saw a day after being in the wild, but this is a minor update, making that uptake rate impressive.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(23, 'Mobile Ad Exchange Nexage Opens Up To Third-Party Services With Nexage Connect', 'Nexage is hoping to expand the capabilities of its mobile ad exchange with the launch of a new service called Nexage Connect. Mark Connon, the company''s executive vice president of corporate and business development, said advertisers and publishers can do a number of things (largely related to targeting) in the desktop world that they don''t have easy access to in mobile, due to restrictions on third-party cookies. So Nexage is taking a platform-style approach by integrating with outside services.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(24, 'The iPad Mini’s Huge Potential For Retail, Customer Service And Industrial Applications', 'The iPad mini hit store shelves today, and I got the chance to get one for myself. The device is a terrific consumer tablet, but it has a lot of potential to be an even more impressive player in retail, restaurant and industrial applications. The iPad mini''s big brother has done a good job of making headway in businesses and customer service, but the iPad mini has a strong chance to help drive those accomplishments even further.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(25, 'Samsung Tops U.S. Mobile Device Manufacturers, Apple Gains In Hardware And Platform Share', 'Digital metrics firm comScore today released its most recent mobile market share report, detailing trends in the mobile industry for the three month period ending in September. The survey of over 30,000 mobile subscribers in the U.S. revealed that smartphone penetration topped 50 percent for the first time ever in the study''s history, with Android and Samsung leading the charge.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(26, 'Even If It Doesn’t Make Sense, Rumors Of A Facebook Phone Live On', 'A Facebook phone doesn''t make sense. Mark Zuckerberg said it himself on our Disrupt stage just a few months ago. But that doesn''t mean the rumors won''t continue to fly. The latest comes by way of Pocket-Lint, which claims to have source confirmation that the HTC Opera UL phone popping up in benchmarking results is, in fact, a Facebook phone.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(27, 'Nexus 4 Review: Not Exactly Perfect, But Close Enough For Me', 'It seems like ages since Google and HTC first kicked off the Nexus experiment (it was 2010, but who''s counting?). Now here we are, over two years later, and we’re on our fourth Nexus smartphone. There’s no question that the hardware has gotten progressively better, but these past few months have also seen some notable changes in the Nexus brand itself. With devices like the Nexus 7 picking up plenty of mainstream traction (something that the Nexus smartphones were never really able to do), Google now seems to be retooling the rest of its line up to appeal to masses too. Will the Nexus 4 be able to pick up the same steam its older brother can? Or is it still meant mostly for the Android-adoring niche dwellers? Read on for more.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(28, 'Review: Google’s Android OS Might Be Better Suited For Tablets, And The Nexus 10 Is A Shining Example', 'This week, Google announced a new lineup of devices that would be running its Android OS, Jelly Bean version 4.2. Those new devices are a phone and a new 10-inch tablet, called the Nexus 10. I''ve had a chance to play with both devices, specifically the Nexus 10, and I actually surprised myself with how the device has fit into my daily routine.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(29, 'Watch The iPad Mini And Nexus 7 Face Off In A Drop Test', 'The folks over at Android Authority have put together a cute little drop test for the iPad Mini and the Nexus 7, and you may be surprised at which tablet walks away victorious.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(30, 'With Hints For An IPO, Mobile Analytics Startup Flurry Raises $25M From Crosslink', 'It''s taken more than five years to get here, but San Francisco-based mobile analytics and advertising company Flurry is hinting that an IPO might be around the corner. The company just took $25 million in funding led by Crosslink Capital, the late-stage firm that backed Pandora ahead of its offering. Crosslink''s Nick Mignano is joining the board.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(31, 'Samsung Galaxy Note II Phablet Sells Three Million+ In One Month+ Of Sales, ~3X Faster Sales Rate That Original Galaxy Note', 'Turns out there are an awful lot of phablet fans out there. More than three million, in fact, have lined up to cram Samsung''s latest smartphone-cum-tablet, the Galaxy Note II, in their oversized pockets. It took 37 days for the device to pass three million sales. The Korean mobile maker confirmed these figures to AP.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(32, 'In A Bid To Enter The Japanese Market, Kids’ Tablet Maker Fuhu Raises $5M From Telecom KDDI', 'Japan''s second largest cellular provider KDDI just announced the first U.S. deal for its Open Innovation Fund — a $5 million investment in Fuhu. The Los Angeles-headquartered startup offers a number of products and services (when Fuhu raised its Series B from Acer, we described it as an &quot;avatar startup&quot;), but the one that attracted KDDI''s interest is the nabi 2, an Androidtablet built specifically for kids.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(33, 'GetJar Shifts Focus From Distribution To Discovery And Commerce, As Its Virtual Currency Hits 50M Users, 60% Of Revs', 'You may know GetJar as the largest independent, cross-platform app store, and one of the largest distributors of Android apps outside of the Android Market. With more than 2 billion downloads and some 150K+ applications, the company continues to grow. This is thanks in part to its &quot;open&quot; model, which allows third-party app makers to distribute apps for most of the major mobile platforms. Getting there has been no small feat, considering the app store competition includes such boldfaced names as Google, Apple, Nokia, Samsung, Blackberry and Microsoft.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(34, 'LocalVox Buys Postling To Give SMBs A One-Stop Shop For Social Media Management & Local Marketing', 'If you don''t live in New York City, you may not have heard of LocalVox. But the startup, which aims to simplify local marketing for small businesses and publishers, is beginning to take off. Headquartered in the Big Apple, the startup initially focused its efforts in its own backyard, but thanks to the $7.5 million series A it closed two weeks ago, it is now setting its sights on a more national audience. As part of increasing that scope, LocalVox is today announcing the acquisition of Postling, also an NYC-based startup, which makes a social media dashboard that helps small business owners monitor their social streams, get notified when someone posts to their social pages and discover who their most influential followers and customers are one social media.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(35, '“In the Studio,” Bump’s David Lieb Discusses Flock And A Shift To Background Services', '&quot;In the Studio&quot; this week hosts a first-time founder and entrepreneur who began his career as an engineering student at two of the country''s finest universities and played baseball for his college team, after which he worked at one of the country''s largest technology research and product companies before enrolling in and dropping out of business school to work on building a company in Silicon Valley. David Lieb, the co-founder and CEO of Bump Technologies, has been focused on mobile since he dropped out of the MBA program at the University of Chicago to build this company. Since then, Bump has been growing (in terms of downloads) and drives a lot of sharing of photos and contacts among users, especially during weekends. Before starting Bump, Lieb finished engineering degrees working with robots and joined Texas Instruments, where he worked with algorithms. The timing of Bump couldn''t have been better, with the rise of the iPhone and their innovative use of sensors drove them to enjoy great growth.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(36, 'Facebook’s Next Money Maker? Its Version Of AdWords In Its New App Center Search', 'Facebook&nbsp;added a search box&nbsp;to its App Center today, but what it might do next is more interesting. Facebook could soon let developers pay to buy search keyword ads in the App Center search typeahead, similar to the specific name ads in its site-wide search typeahead. And if App Center goes beyond the typeahead and launches a search results page, it could host full-blown AdWords-style search ads.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(37, 'Apple’s North American Mobile Web Traffic Surges Following iPhone 5 Launch', 'Apple''s share of mobile web traffic rose 5.96 percent between September and October, thanks no doubt to the introduction of the iPhone 5, and likely also helped by the shipping of the 4th generation iPod touch. Apple controlled 65.38 percent of the traffic advertising network and research firm Chitika saw across the millions of devices, and Samsung gave up the most ground.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(38, 'Apple Releases iOS 6.0.1 With Fixes For Keyboard, Wi-Fi, And OTA Updating For iPhone 5', 'iOS 6.0.1 arrives today ahead of the iPad mini, and it features some bug fixes that resolve issues around the famous horizontal lines appearing on the software keyboard bug, a camera flash issue, and improved Wi-Fi connectivity reliability. This is the first minor point upgrade for iOS 6, and should solve some of the update''s growing pains. But for iPhone 5 owners hoping to download it OTA, you''ll first have to update your updater, since one bug fixed here prevents wireless updating.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(39, 'Move Over BlackBerry: Pentagon Opens Up The Possibility Of Adding iPhone Or Android Devices', 'The U.S. Government will now be looking into solutions for provisioning iOS and Android devices in addition to those featuring RIM''s BlackBerry OS, according to a new report from Reuters. That means RIM will no longer have the exclusive contract for smartphone devices at the U.S. Defense Department, though it will remain another option.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(40, 'What If Apple Went In On A Mobile OS Mashup?', 'Three mobile operating systems dominate the market: iOS, Android, and now Windows Phone 7/8. Each has their interface strengths — iOS is clean where Android is customizable where WP8 is dynamic. But what if you could have the best of all three? Designer and Wrapp cofounder Max Rudberg has attempted to portray this hybrid OS, putting live tiles and widgets on the iOS home screen.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(41, 'Cops nab Khemka threat caller in Gurgaon', 'Haryana police on Friday arrested a person in Gurgaon for making a threat call to whistleblower IAS officer Ashok Khemka.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(42, 'Manpreet proposes alliance with Congress for ''stronger opposition''', 'The Peoples Party of Punjab (PPP) President Manpreet Singh Badal on Friday proposed an alliance with Congress for a &quot;stronger opposition&quot; against the ruling SAD-BJP combine.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(43, 'Woman shot at Karva Chauth party in Bathinda', 'A woman attending a Karva Chauth party at a local hotel was allegedly shot by an unknown assailant on Friday.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(44, 'Khaps say violence no answer to check rape', 'Haryana''s khap leaders have said they don''t believe in violent means to check rape cases in the state but are in favour of &quot;strict measures&quot; to deal with the crime.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(45, 'Salman Khurshid takes a jibe at IAC', 'Foreign minister Salman Khurshid said on Friday that he was not going to get bogged down by the campaign launched against him by India Against Corruption (IAC), insisting that he had the responsibility to speak for India &quot;with or without banners&quot;.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(46, 'Mining banned, but department blind to quarrying in Satluj Yamuna Link', 'Despite a ban on illegal mining of sand by Punjab and Haryana high court, quarrying continues unabated in the bed of Satluj Yamuna Link (SYL) canal, near Punjab-Haryana border, under Ghanaur constituency of Patiala district.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(47, 'Cops withdrawn from Asthana allege harassment', 'Despite the suspension of IGP SK Asthana by the Punjab government for retaining unauthorized manpower, some of the cops who were sent back on their respective places of posting have alleged that they are being harassed by the department for complaining about the matter.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(48, 'Haryana whistleblower gets relief from CAT', 'The Delhi bench of Central Administrative Tribunal has intervened on behalf of the whistleblower from Haryana, Sanjiv Chaturvedi, and restrained its Chandigarh bench from hearing a case against him. The regional bench has, as one of its members, a former state chief secretary involved in his suspension.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(49, 'Sukhbir, Dhindsa to discuss with Pakistan govt', 'Apart from the peaceniks, the Punjab government is also concerned over the Pakistan government''s decision to put on hold the renaming of a roundabout in Lahore after martyr Bhagat Singh.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(50, 'Bibi comes out of jail in victory procession', 'Former SGPC chief Bibi Jagir Kaur was released from Kapurthala Model jail, where her supporters had gathered outside the gate to extend her &quot;moral support.&quot;', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(51, 'Officer-men face-off in Army, soldiers refuse ''menial jobs''', 'In yet another incident of face- off between Army officers and men, 10 soldiers refused to carry out alleged ''menial jobs'' ordered by their superiors in Patiala in Punjab and returned to their regimental centre in protest.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(52, '1984 ''genocide'' plea tabled in Australian parliament', 'A petition was tabled in Australian parliament on November 1, seeking to declare the killings of Sikhs in 1984 as &quot;genocide&quot;.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(53, 'Election Commission presses govt to promulgate Ordinance to ban opinion polls', 'The Election Commission (EC), worried over the “undue influence&quot; of opinion polls on voters in election-bound Himachal Pradesh and Gujarat, has been pressing the government to promulgate an Ordinance to ban conduct of opinion polls and dissemination of their results until completion of polling.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(54, 'Push for digitization of driving licences, vehicle registration papers', 'The total number of registered vehicles stands at 15.25 crore, over 10% of country’s entire population and there are 8.46 crore drivers with valid driving licences.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(55, 'CIC to CBI: Disclose info related to graft cases', 'Information related to cases of corruption should be disclosed exempted under the RTI Act, the Central Information Commission (CIC) told CBI.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(56, 'Paramilitary forces sore with Centre''s cosmetic policy makeover', 'Paramilitary forces are not too enthused by the government''s Thursday announcement of declaring their retired personnel as ''ex-central police personnel''.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(57, 'Contractor lobby controls ICDS supply in several states', 'Maharashtra is not the only state where contractors control the lucrative ICDS rations supply system that is worth over Rs 8,000 crore annually across the country.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(58, 'Gadkari can''t be singled out, says RSS', 'Despite his absence, BJP president Nitin Gadkari lurked on the sidelines of the RSS national executive council meeting that got underway here on Friday.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(59, 'Maharashtra food scam: Private companies eat up Rs 1,000cr meant for poor', 'A corporate-contractor lobby has hijacked the Integrated Child Development Scheme meant to provide food to poor children and their mothers, said a report.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(60, 'CAG does not meet reps of audited entities', 'A day after the petroleum ministry had to cancel a meeting between RIL officials and the Comptroller and Auditor General, the auditor asserted that it was entitled to access all documents related to Reliance’s contract with the government for exploration of KG basin gas.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(61, 'BJP to end boycott of Joint Parliamentary Committee', 'BJP is likely to attend the next meeting of the Joint Parliamentary Committee probing the 2G scam on November 8, ending its boycott of the panel to once again press for the inclusion of Prime Minister Manmohan Singh and finance minister P Chidambaram as witnesses.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(62, 'Centre seeks time-bound action against ''tainted'' babus', 'In a move that may benefit a number of government officials who have to face corruption charges on flimsy grounds, the Centre has directed all ministries not to deny vigilance clearance — one of the prerequisites of being considered for promotion – to officials only because of pending disciplinary, criminal or court cases against them.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(63, 'Abhishek Verma''s wife denied separate jail van', 'A trial court on Friday refused to provide a separate van to businessman Abhishek Verma''s Romanian wife Anca Maria Neascu, who is allegedly getting extortion threats from undertrial prisoners, to ferry her from Tihar Jail in connection with various cases.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(64, 'Coalgate: SC dismisses CAG''s loss figure review plea', 'The Supreme Court todayday dismissed a petition seeking direction to the comptroller and auditor general (CAG) to revisit its valuation of loss of Rs 1.86 lakh crore to the exchequer in the controversial allocation of coal blocks.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(65, 'National Commission for Scheduled Castes calls Haryana as ''balatkar pradesh''', 'Speaking to reporters today, National Commission for Scheduled Castes chairman PL Punia dubbed Congress-ruled Haryana as &quot;balatkar pradesh&quot; (rape state).', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(66, 'Heavy rain disrupts normal life in Odisha', 'Incessant rain over the past two days has thrown life out of gear in parts of Odisha, disrupting road traffic and causing damage to paddy crop in some areas, a weather official said on Saturday.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(67, 'Bodies of all five sailors recovered', 'Bodies of all five sailors, who went missing after an oil tanker ran aground off the city beach hours before cyclone ''Nilam'' made landfall on November 1, have been recovered.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(68, 'Pan Singh Tomar’s great grandson is a banking correspondent in Morena village', 'Anuj Singh Tomar works as a banking correspondent in village Kosarlkalan in Morena, near Gwalior. He is part of a new army that forms the last mile in Madhya Pradesh government''s ambitious financial inclusion programme.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(69, 'Akhilesh Yadav withdraws criminal cases against Bhatta-Parsaul farmers', 'The cases were lodged against farmers following violent clash with the police in Bhatta-Parsaul in Gautambudhnagar in May, 2011. The farmers were agitating against land acquisition by the then Mayawati government.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(70, 'Lawyer accuses cops of stalling Salman’s case', 'After raining fire on Arvind Kejriwal and Sharad Pawar , former cop-turned-lawyer Y P Singh has turned his gunsights on Mumbai police.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(71, 'Digitization of driving licences gathers pace', 'Just ahead of the government pushing for legislation to allow higher penalty for repeating traffic offences, the road transport and highways ministry has stepped up digitization of all driving licences (DLs) and vehicle registration certificates (RCs).', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(72, 'China invites India''s ''Missile man'' to teach at Peking varsity', 'China has invited India''s &quot;missile man&quot; and former President Dr A P J Abdul Kalam to teach at the prestigious Peking University here, offering him a lab of his choice to work with the students.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(73, 'Israel trashes branding of its soldiers in Goa as dehumanised', 'A senior Israeli diplomat today assailed the remarks of an outfit linked to Goa Church that Israeli soldiers holidaying in Goa are &quot;dehumanised&quot; and termed the same as &quot;nonsense&quot;.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(74, 'Israel trashes branding of its soldiers in Goa as dehumanised', 'A senior Israeli diplomat today assailed the remarks of an outfit linked to Goa Church that Israeli soldiers holidaying in Goa are &quot;dehumanised&quot; and termed the same as &quot;nonsense&quot;.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(75, 'Zambia seeks MEA help to make Kingfisher pay pilot', 'The ongoing Kingfisher crisis has now acquired diplomatic overtones. The Zambian high commission has approached the ministry of external affairs (MEA) to take up the issue of non-payment of salary - $24,000 or about Rs 13 lakh - to one of its nationals who worked as a pilot in the currently grounded airline.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(76, 'BJP: Tax-free money given to business entity', 'Taking on the Congress on the last day of campaigning for the Himachal Pradesh polls, senior BJP leader Arun Jaitley dared the party to come clean on Subramanian Swamy''s charge of money collected by the AICC for &quot;political purpose&quot; - for which it gets tax exemption - being transferred to a commercial entity.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(77, 'Kalyan Singh set to return to BJP on his terms', 'Former Uttar Pradesh chief minister and Jan Kranti Party leader Kalyan Singh is set to return to the BJP on his terms as the national vice-president, the same post he held at the time of leaving the party in 2009.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(78, 'General V K Singh''s political activism leaves military men embarrassed', 'Many in military circles believe that Gen Singh could be positioning himself for 2014 polls. &quot;He seems to be testing out various forums,&quot; a senior serving officer said.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(79, 'If separated, one conjoined twin will die and other''s survival difficult: Centre', 'A surgery to separate teenaged conjoined twins Saba and Farah could prove fatal for one and pose grave danger to the life of the other, the Union government informed the Supreme Court on Friday.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(80, 'Congress defends Rahul, rubbishes Swamy''s charges', 'A day after Janata Party chief Subramanian Swamy hurled several allegations against the Gandhi family, the Congress said it had provided interest-free loans to the ailing company that ran National Herald without any commercial profits.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(81, 'Abhishek Singhvi back in Congress''s media panel', 'Rajya Sabha MP Abhishek Singhvi is set to return as Congress spokesman after ceding the key responsibility a few months ago.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(82, 'Singhvi back in Congress''s media panel', 'Rajya Sabha MP Abhishek Singhvi is set to return as Congress spokesman after ceding the key responsibility a few months ago.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(83, 'IAC claims shoe-thrower a ''close aide'' of Gandhi family', 'India Against Corruption (IAC) on Friday charged Jagdish Sharma, who had hurled a shoe at Arvind Kejriwal disrupting the press conference on Wednesday, of being a &quot;close aide&quot; of the Gandhi family.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(84, 'Centre plans to lower widow pension age to 18, expand it to single, divorced women', 'The Centre may reduce the eligibility age for ''widow pension'' from 40 years to 18 years, a move that would bring the welfare scheme in sync with social realities.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(85, 'LPG cap may be raised to 9 subsidized cylinders per household', 'Govt is set to increase the number of subsidized LPG cylinders per household from 6 to 9 as soon as the code of conduct runs out in poll-bound Himachal and Gujarat.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(86, 'LPG cap may be raised to 9 subsidized cylinders', 'Govt is set to increase the number of subsidized LPG cylinders per household from 6 to 9 as soon as the code of conduct runs out in poll-bound Himachal and Gujarat.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(87, 'President warns against vilification campaigns', 'President Pranab Mukherjee on Friday cautioned against “vilification campaigns” to defame or destroy reputations in the name of combating graft.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(88, 'President warns against vilification campaigns', 'President Pranab Mukherjee on Friday cautioned against “vilification campaigns” to defame or destroy reputations in the name of combating graft.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(89, 'Kukis threaten to resume armed struggle for statehood', 'The Kuki National Organization (KNO), an umbrella body of 16 Kuki outfits, has threatened to resume armed struggle and secede from Manipur if the Centre did not begin talks with them before November 22.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(90, '''Cricketing ties with Pak no dilution of stand on 26/11''', 'Even as he spoke in favour of resuming cricketing ties with Pakistan, the new foreign minister, Salman Khurshid, said on Friday that there is no question of any dilution of India''s position on 2008 Mumbai attacks.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(91, 'Gaya''s Mahabodhi temple beefs up security', 'The Gaya district administration has tightened security and set up a control room to monitor activities at world-famous Mahabodhi temple complex 24X7 in view of threat of a militant attack.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(92, 'Himachal ready for assembly elections tomorrow', 'Out of total 7,253 polling booths, 1,310 booths have been declared as sensitive while 767 booths have been categorized as hydro sensitive.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(93, 'CAG should look into his ''leaky office'': Digvijaya Singh', '&quot;CAG interim report again leaked. Would CAG instead of sermonising every one please look into his Leaky Office?&quot; Digvijaya Singh wrote on Twitter.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(94, 'BJP make efforts to retain estranged Yeddyurappa in the party', 'The BJP has stepped up efforts in its mission to mollify estranged former chief minister B S Yeddyurappa in the party.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(95, 'RSS demands deportation of Bangladeshi infiltrators', 'Holding that Bangladeshi infiltrators have spread all over the country, RSS on Saturday demanded the Centre and state governments take steps to check the border, detect and deport them.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(96, 'Herald House row: Congress says emotional issue', 'After Janata Party chief Subramanian Swamy urged the Election Commission to derecognise the Congress for giving an interest-free loan to Associated Journals Ltd, the Congress on Saturday said it was an &quot;emotional issue&quot;.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(97, 'Sukhbir Badal to visit Pakistan, explore deeper trade ties', 'Punjab deputy chief minister Sukhbir Singh Badal said he would visit Pakistan on Monday to explore strengthening trade between &quot;eastern&quot; and &quot;western&quot; Punjab.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(98, 'Mulayam withdrawing cases against Amar Singh shameful, tweets Kejriwal', '&quot;Money laundering cases withdrawn against Amar Singh? Shameful.Is this criminal justice system? We have to completely overthrow this system,&quot; he tweeted.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(99, 'Mulayam withdrawing cases against Amar Singh shameful, tweets Kejriwal', '&quot;Money laundering cases withdrawn against Amar Singh? Shameful.Is this criminal justice system? We have to completely overthrow this system,&quot; he tweeted.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(100, 'Heritage heartache: Stalled work on Hindu shrine dismays Pakistanis', 'India and Pakistan hit a high in bilateral ties when BJP leader L K Advani laid the foundation stone for the restoration of Katas Raj Temple complex near Chakwal in Pakistan'' Punjab in 2005.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(101, 'Family ties marked by warmth and mistrust', 'When Rahul met Omar in J&amp;K recently, they carried forward a legacy which began with Nehru and Sheikh Abdullah.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(102, 'Public authorities failed to comply with RTI provisions: CIC Satyananda Mishra', 'Chief information commissioner Satyananda Mishra on Saturday slammed public authorities for failing to comply with the suo-motu disclosure provisions of the Right to Information Act even after seven years of the implementation of the Act.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(103, 'Modi''s Gujarat is vibrant only for his five pet industrialists: Gujarat Congress', '&quot;We are exposing Modi''s lies. ''Vibrant Gujarat'' is a myth. Modi''s Gujarat is vibrant only for his five pet industrialists, not for the aam aadmi,&quot;said Shaktisin Gohil.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(104, 'Allocation for health sector enhanced three-fold: PM', 'Recognising the need to address complex challenges in the health sector, Prime Minister Manmohan Singh on Saturday said allocation for it has been enhanced by about three times in the 12th plan period with enhanced thrust on nutrition and sanitation.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(105, 'TDP leader Yeran Naidu cremated at his native village', 'Former Union minister and senior TDP leader Kinjarapu Yerran Naidu, who died in a road accident, was cremated with state honours at his native Nimmada village in north coastal Andhra district on Sunday morning.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(106, 'Himachal Pradesh set for assembly polls tomorrow', 'Led by chief minister Prem Kumar Dhumal, the incumbent BJP is hoping to script history in Himachal by going the Punjab way, where the Akali-BJP combine returned to power against historical projections.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(107, 'UN Security Council out of tune, serves no one''s purpose: India', 'India, which assumed this month''s presidency of the Security Council, said the UN body has remained ''polarised and politically divided'' on key issues like Syria.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(108, 'LPG will prove to be Cong nemesis: HP chief minister Prem Kumar Dhumal', 'In an interview to Parshant Krar, Himachal Pradesh chief minister Prem Kumar Dhumal says ''self-employment'' is the answer to growing unemployment.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(109, 'Subramanian Swamy seeks derecognition of Congress party, to file petition before Election Commission', 'Subramanian Swamy is seeking derecognition of the Congress party for giving an unsecured loan of Rs 90 crore to the Associated Journals.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(110, 'Naga IAS officer builds 100-km road in Manipur without govt help', 'A young Naga IAS officer, without any funding from the government, has built a 100-km long load with the help of villagers in remote Manipur.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(111, 'Himachal ready for assembly elections today', 'Out of total 7,253 polling booths, 1,310 booths have been declared as sensitive while 767 booths have been categorized as hydro sensitive.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(112, 'RIL ‘appreciates’ CAG for not conducting performance audit on private operators', 'Mukesh Ambani led RIL on Saturday clarified that it has no objection to get its books of accounts audited by the federal auditor, the Comptroller and Auditor General of India and citing media report appreciated that the federal auditor does not conduct performance audit of private operators.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(113, 'Burhanpur in Madhya Pradesh to be developed as world heritage site', 'Tourism minister Tukojirao Puar has said in view of immense tourism potential in Burhanpur district, it will be developed as world heritage site.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(114, 'Mamata prepares for rural polls, calls meeting of all political members on November 6', 'More than governance, chief minister Mamata Banerjee is now concentrating on matters political. With the panchayat election due in 2013, the Trinamool supremo has called a meeting of all political members from the city and the districts on November 6 at Khudiram Anushilan Kendra.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(115, '26/11 case in Pakistan: Prosecution seeks speedy trial', 'Day-to-day hearings should be held for an early decision in the Mumbai attack case in Pakistan involving LeT''s Zakiur Rehman Lakhvi and six other suspects, the prosecution has demanded.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(116, 'Modi''s Gujarat is vibrant only for his five pet industrialists: Gujarat Congress', '&quot;We are exposing Modi''s lies. ''Vibrant Gujarat'' is a myth. Modi''s Gujarat is vibrant only for his five pet industrialists, not for the aam aadmi,&quot;said Shaktisin Gohil.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(117, 'Two held with narcotics worth Rs 3 crore at Hyderabad airport', 'Two persons were arrested at the international airport in Hyderabad after 10 kg of narcotic drug worth Rs 3 crore was allegedly found in their possession.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(118, 'Displaced in Myanmar need food, shelter: UN refugee agency', 'Over 35,000 people have been displaced in the new wave of unrest in Myanmar''s Rakhine state and they are in urgent need of food and shelter, according to UN Refugee Agency (UNHCR).', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(119, 'US jury awards troops $85 million over Iraq chemical exposure', 'An Oregon jury awarded 12 Army National Guardsmen $85 million in damages from defense contractor KBR Inc. after finding that the company failed to protect them from exposure to cancer-causing chemicals when they served in Iraq.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(120, 'New York marathon canceled after storm backlash', 'New York City is canceling its annual marathon, scheduled for Sunday, after a backlash against plans to hold the race in the wake of hurricane Sandy, Mayor Michael Bloomberg said on Friday.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(121, '6.1 earthquake hits off southern Philippines: US seismologists', 'A strong 6.1-magnitude earthquake struck off the southern Philippine island of Mindanao early Saturday, US seismologists said, but there were no immediate reports of damage or casualties.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(122, 'Pakistani rights activist Marvi Sirmed attacked by gunmen in Lahore', 'Unidentified gunmen attacked prominent rights activist Marvi Sirmed today, who has received threats from extremist groups several times in the past', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(123, 'At least 16 mild tremors rocked Saurashtra and Kutch on Friday', 'At least 16 mild tremors rocked Saurashtra and Kutch on Friday. Officials at the Institute of Seismological Research, Gandhinagar, said 10 of these tremors were experienced across Kutch district, while the remaining were felt in Saurashtra region.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(124, 'China proposes joint collaboration for space solar power mission with India', 'China today rolled out a red carpet to &quot;Missile man&quot; and ex-President APJ Abdul Kalam on his first visit to the country, proposing a joint collaboration for a space solar power mission with India and inviting him to teach at the prestigious Peking University here.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(125, 'Millions still without power in New York, New Jersey after Sandy', 'Over 2.7 million homes and businesses in New York and New Jersey were still without power, four days after Hurricane Sandy slammed the US east coast.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(126, 'DRDO director- general unveils Delhi Technological University''s Aarush X1', 'The students of Delhi Technological University have designed and developed Aarush X1, next generation unmanned aerial vehicle for urban applications, with the mentoring support from Lockheed Martin, a US avionic company.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(127, 'DRDO director- genneral unveils Delhi Technological University''s Aarush X1', 'The students of Delhi Technological University have designed and developed Aarush X1, next generation unmanned aerial vehicle for urban applications, with the mentoring support from Lockheed Martin, a US avionic company.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(128, 'Congress''s no response to CIC notice on bringing political parties under transparency law', 'All the other major political parties responded to the notice but said that they are dead against coming under RTI as it can be used to seek information about their income and donors.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(129, 'Union home minister positive on Telangana talks, Telangana Rashtra Samithi shows thumbs down', 'As the TRS is gearing up for the state committee meeting to be held in Kareemnagar, the party leaders including politburo members, MLAs and MPs would confer on the plans of agitation in the brain storming session.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(130, 'Noted filmmaker Ravi Chopra discharged from Mumbai hospital', 'The filmmaker, who is said to be under financial strain, also produced Amitabh Bachchan starrer ''Bhoothnath'' in 2008. He was in hospital due to lung ailment.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(131, 'Ex-Army chief V K Singh backs farmers'' body opposing sugar decontrol', 'Former Army chief V K Singh on Friday backed a farmers body''s demand for rejection of the Rangarajan Committee report on freeing the sugar sector and threatened to gherao Parliament next month if it is not met.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(132, 'Omar Abdullah returns ‘extra’ LPG connections', 'Jammu &amp; Kashmir chief minister Omar Abdullah on Saturday surrendered his two gas connections, a day after local media reported that he had extra connections in Srinagar in his name.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(133, 'Haldia equipment operator sacks 348 employees', 'Port equipment operator Haldia Bulk Terminals, which has started winding up its operations from West Bengal, retrenched its total workforce of 348 employees Saturday.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(134, 'Public authorities still do not comply with RTI rules: CIC', 'Chief Information Commissioner Satyananda Mishra on Saturday lamented that public authorities have failed to make crucial pro-active disclosures despite seven years of the implementation of the RTI Act.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(135, 'Is real estate value behind loan to Associated Journals Private Limited, BJP asks', 'BJP ratcheted up its attack on Congress for giving a Rs 90-crore loan to Associated Journals Private Limited in alleged violation of the Representation of People’s Act, rejecting Congress’s defence that it extended financial assistance to the publisher of now-defunct National Herald and Qaumi Awaaz papers to propagate party’s ideology.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(136, '‘Aid to National Herald was political dharma’', 'Congress on Saturday rubbished Janata Party chief Subramanian Swamy’s allegations, saying the loan given to Associated Journals for promoting ideology of Mahatma Gandhi and Jawaharlal Nehru is for a political purpose.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(137, '15 choppers to be used to flush out Naxals from Andhra-Odisha Border', 'Andhra Pradesh DGP said that 15 helicopters would be deployed soon to track and flush out Maoists from the dense jungles of Andhra-Odisha Border.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(138, 'Swamy asks Election Commission to derecognize Congress', 'A day after Congress said that it had loaned money to Associated Journals, Janata Party leader Subramanian Swamy latched on what he called “written admission” to ask Election Commission (EC) to de-recognize Congress as a political party.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(139, 'Hillary Clinton greets new foreign min Khurshid', 'Foreign minister Salman Khurshid on Saturday conveyed ``deep sympathies'''' to the US over the devastation caused by Hurricance Sandy in what was his first conversation with the secretary of state Hillary Clinton after taking over.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(140, 'Health sector allocation up 3 times: PM', 'Prime Minister Manmohan Singh on Saturday said allocation for health sector during the 12th Plan period has been enhanced by three times over the previous plan.The outlay for the sector has been fixed at Rs 3 lakh crore, which accounts for 1.95 per cent of the GDP.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(141, 'Strategic areas to get quick launch bridges', 'Border Roads Organization (BRO) wants second generation “quick launch bridges” to accelerate supply of machinery and material for major infrastructure projects in border areas, particularly in strategic regions.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(142, '‘Indian cities have much to learn from New York''s disaster response & limitations’', 'It took a little more than a day of hurricane-force winds and an almost 14-foot tidal surge to reduce this global financial powerhouse into a city of haves and have-nots—those who had electricity and those who did not.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(143, 'School yet to revoke teacher suspension over dress code', 'When Kollathodi Jameela, an aggrieved and suspended for 15 days mathematics teacher, went to Sullamussalam Oriental High School (Areekode) on Saturday the management refused to let her resume work.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(144, 'Ban plastic tricolours, says MHA', 'The government has heeded pleas of environmentalists and also kept in mind the dignity of the national flag by advising ministries and states to ensure only paper is used in making small flags used by thousands on occasions like Independence and Republic Days.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(145, 'Tweak norms, give houses to poor: Centre', 'The Union housing ministry has urged state governments to amend development control norm to ensure private developers reserve at least 30%-35% of their dwelling units or 15%-20% of floor area ratio (FAR) in each project for economically weaker sections.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(146, 'LPG refill delayed? Dealer may be fined', 'Soon, cooking gas dealers will have to pay a fine if they fail to deliver a refill within 48 hours of booking. This is one of the provisions the oil ministry is working on for inclusion in the revised marketing guidelines for state-run fuel retailers.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(147, 'Pro-reform push in Congress show of strength today', 'Along with Prime Minister Manmohan Singh and Congress chief Sonia Gandhi, Rahul Gandhi is billed to be the star draw at the show of strength.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16');
INSERT INTO `article` (`articleid`, `title`, `body`, `upvote`, `downvote`, `viewed`, `timestamp`, `popularityscore`, `publicationdate`) VALUES
(148, 'Search engine in a spot over sex-selection ads', 'Popular search engine Google may be in for some trouble for continuing to display advertisement links of clinics offering gender selection of unborn children. Pro-girl child groups in India have written to the company, asking it to remove these links within a fortnight or face legal action.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(149, '‘Anti-govt’ Bengal film screening stopped', 'The decision by a theatre run by Kolkata Municipal Corporation to stop Saturday’s scheduled screening of a just-released movie that alluded to the now infamous Park Street rape has got tongues wagging about CM Mamata Banerjee’s intolerance.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(150, 'Gulshan Rai tipped to be first head of national cyber security agency', 'The government would soon announce the appointment of the first National Cyber Security Coordinator (NCSC), stepping up its effort to put up a coordinated response to the various challenges in cyber space.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(151, 'Kejriwal slams Mulayam for withdrawing case against Amar Singh', 'Reacting to Uttar Pradesh Police submitting a closure report in a money laundering case against former Samajwadi party leader Amar Singh, IAC''s Arvind Kejriwal called the move ``shameful.’’', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(152, 'PMLA case against Amar Singh may die down', 'The Enforcement Directorate’s money laundering case against former Samajwadi Party (SP) leader Amar Singh looks set to fall through with the Uttar Pradesh Police filing a closure report deciding to withdraw economic offence charges against him.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(153, 'Present UNSC structure serves no one: India to UN', 'India has said the UN Security Council in its present structure is “completely out of tune” with global realities and serves “no one’s purpose”, asserting that an expansion in the 15-member body should have permanent representation from Asia, Africa and Latin America.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(154, 'Environment ministry gives nod for mining in Mahan Coal Block', 'The environment ministry has finally given its nod to mine the Mahan Coal Block in Singrauli coalfield in Madhya Pradesh by clearing 1,182.35 hectare of forestland.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(155, 'CAG calls for corporate accountability', 'At a time when his office is locked in a bitter tussle over the audit of KG-D6 gas fields, CAG Vinod Rai has emphasized the need for corporate accountability.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(156, 'Abu Jundal ‘brain’ behind 2006 Aurangabad arms haul plot', 'The Anti-Terrorism Squad (ATS) on Saturday filed a supplementary chargesheet in the special court against suspected Lashkar-e-Taiba (LeT) operative Zabihuddin Ansari alias Abu Jundal in the 2006 Aurangabad arms haul case.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(157, 'Will Nitish''s visit give a boost to Biharis in Pakistan?', 'Biharis have enriched Karachi’s cosmopolitan culture and their imprint on the city is perhaps best reflected in making famed Bihari kebabs an integral part of its culinary attractions.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(158, 'Ruchika suicide: School challenges SDM''s report in civil court', 'Chandigarh-based Sacred Heart School, which had come under the scanner following the report of SDM Prerna Puri holding the school responsible for expelling Ruchika, has challenged the said report before a civil court.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(159, 'Man who threatened Khemka sent in 3-day police remand', 'A Panchkula court on Saturday sent Umed Singh, a former employee of the Haryana Housing Board, who had allegedly threatened whistleblower IAS officer Ashok Khemka, to three-day police remand.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(160, 'Mewat rates 5th lowest in polio vaccination', 'Muslim organizations have appealed to people of Mewat to come forward for polio immunization, especially pulse polio for their children. The appeals have been issued as the district has the poorest track record in polio immunization.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(161, 'HC notices to Punjab, Haryana, UT on issue of FIRs', 'Taking up a public interest litigation (PIL) seeking directions for uploading copies of FIRs on official websites immediately after registration, Punjab and Haryana high court on Saturday issued notice to Punjab, Haryana and Chandigarh asking them to respond on the issue.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(162, 'SAD councillor held for illegal sand mining', 'Police on Friday evening arrested a SAD councillor on charges of involvement in illegal sand mining. The mining department officers caught Sukhjinder Singh, a councillor in Ghagga Nagar Council, while he was illegally carrying out mining along the Kulwanu road near Ghagga using a earthremover.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(163, '''Political instability affects medical research''', 'Some of the health policies and research work have got stuck due to the fear of instability at the Centre, feels Prof Seyed E Hasnain, member of Scientific Advisory Council to the PM and Scientific Advisory Council to Union Cabinet.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(164, 'Begum Khaleda Zia meets President', 'Former PM of Bangladesh Begum Khaleda Zia called on President Pranab Mukherjee on Saturday as part of her week-long visit to India.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(165, 'India, Canada trying to resolve end-user pact imbroglio', 'Ahead of Canadian PM Stephen Harper’s visit to India, the two countries are still battling differences over an end-user agreement, which Canada wants not just to supply uranium to India but also monitor how it is used.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(166, 'Hillary Clinton greets new foreign minister Khurshid', 'Foreign minister Salman Khurshid on Saturday conveyed ``deep sympathies'''' to the US over the devastation caused by Hurricance Sandy in what was his first conversation with the secretary of state Hillary Clinton after taking over.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(167, 'PM, Sonia to push reforms agenda at Sunday mega rally', 'The Congress'' top leadership, including PM Manmohan Singh and party chief Sonia Gandhi, will address a mega rally of party workers at the Ramlila ground today.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(168, 'Gujrat ex-governor’s death takes Modi to Bihar after 2 years', 'Chief minister Narendra Modi will go to Patna on Sunday to offer condolences to the family of former governor of Gujarat and veteran BJP leader Kailashpati Mishra, who died on Saturday after a prolonged illness.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(169, 'Man who threatened Ashok Khemka sent in 3-day police remand', 'A Panchkula court on Saturday sent Umed Singh, a former employee of the Haryana Housing Board, who had allegedly threatened whistleblower IAS officer Ashok Khemka, to three-day police remand.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(170, 'Car bomb explosion at Benghazi police station injures 3 police officers', 'A car bomb exploded on Sunday in front of a police station in Benghazi, injuring three police officers in the latest in a series of attacks on security officials in Libya''s largest city.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(171, 'Two Indian warships dock in Mozambique as part of a goodwill visit', 'The vessels arrived in Maputo Friday night from South Africa. &quot;They will be in our port for two days,&quot; Defence Minister Filipe Nyusi told Xinhua Saturday.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(172, 'Six die in Pakistan suicide blast in Khyber Pakhtunkhwa province', 'Five people died in Pakistan Saturday when a suicide bomber rammed his explosives-laden motorbike into the office of pro-government militiamen.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(173, 'Polling starts bang on time in Himachal Pradesh for the assembly elections', 'The polling for the 68 seats of Himachal Pradesh assembly began amid tight security today morning. Electors across the state could be seen reaching polling stations even before voting began at 8 a.m.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(174, 'Mediators to push Mali Islamists to cut al-Qaida ties', 'Senior mediators in the Mali crisis will try to convince one of the Islamist groups controlling the country''s north to cut ties with al-Qaida''s North Africa branch, an official said Saturday, as an Algerian newspaper said the group was considering such a move.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(175, 'Sudan blocks UN force from investigating deaths', 'The international peacekeeping force in Sudan''s Darfur region has said that its forces were blocked by the military from reaching the destination of an alleged attack that killed 10 people.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(176, 'Democrats need stronger Illinois showing to retake US House of Representatives', 'Democrats appear unlikely to regain a majority in the US House of Representatives on Tuesday despite a nationwide push, and a major reason could be disappointment in President Barack Obama''s home state.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(177, 'Peres calls Palestinian authority president ''true'' partner for peace, Netanyahu differs', 'Israel''s Nobel laureate Shimon Peres was at loggerheads with Prime Minister Benjamin Netanyahu as the two leaders stood differed in their views over an interview given by Palestinian authority president Mahmoud Abbas in which he shunned violence against Israel.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(178, 'New York power problems improve, but fuel rationed', 'Electricity finally returned to Manhattan on Saturday and the subway was running more smoothly, but severe gasoline shortages threatened New York''s attempt to recover from superstorm Sandy.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(179, 'Modi''s Gujarat is vibrant only for his five pet industrialists: Gujarat Congress', '&quot;We are exposing Modi''s lies. ''Vibrant Gujarat'' is a myth. Modi''s Gujarat is vibrant only for his five pet industrialists, not for the aam aadmi,&quot;said Shaktisin Gohil.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(180, 'After U.K. Court’s Handslap, Apple Gets Less Cheeky In Samsung “Apology”', 'Apple had to tone down the attitude in a re-issued &quot;apology&quot; it published today to its U.K. website after losing an appeal in a patent infringement case against Samsung.&nbsp;A U.K. judge smacked down its previous apology for being &quot;incorrect&quot; and &quot;untrue&quot; on Thursday. Above is the pared-down version that appears in a link at the bottom of the company''s U.K. homepage.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(181, 'Two CISF jawans killed in naxal attack in Chhattisgarh', 'Dantewada SP Narendra Khare said naxals, disguised as locals, attacked CISF personnel deployed at the mines of National Mining Development Corporation in Akash Nagar area of Dantewada.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(182, 'Himachal Pradesh polls: Voting picks up after slow start', 'By 12 noon, long queues of voters, of different age-groups could be seen outside various polling booths in Shimla and Solan.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(183, 'Himachal Pradesh polls: Former CM Virbhadra Singh casts vote', 'Virbhadra Singh is contesting from Shimla (rural) constituency of the district for the first time.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(184, 'Himachal Pradesh elections: About 10 per cent polling in 2 hours', 'Polling in the hill state of Himachal Pradesh started on a slow note with about 10 per cent of the voters exercising their franchise in the first two hours of the election day.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(185, 'Congress lists UPA achievements at mega rally', 'In a bid to give a push to the government''s economic agenda, PM Manmohan Singh, Sonia Gandhi and Rahul Gandhi have arrived at the Ramlila Maidan.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(186, 'We will pass Lokpal Bill, just wait and watch: Rahul Gandhi', 'Rahul Gandhi today promised to get the Lokpal Bill passed in parliament in the coming months, even as he attacked the opposition for preventing the anti-corruption legislation''s passage earlier.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(187, 'The Need For Mobile Money Spawns A Startup Ecosystem Across Africa', '<b>Editor’s Note:</b> \n<em>Vanessa Clark is the co-founder of Mobiflock, a mobile safety and security company offering&nbsp;parental control services for smartphones and tablets. She is involved in the mobile industry in Africa.</em> Zimbabwean Tawanda Kembo, 25, has been employed for more than two years but still doesn’t qualify for a credit card. This is the result of banking restrictions on issuing credit cards, he says, and around 70 percent of his fellow Zimbabweans are in the same boat. This is not unusual across the entire African continent, which has a similar unbanked or underbanked rate, according to the World Bank.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(188, 'Now You Can Ask Your Smartphone About The Past, Present Or Future: Android’s Siri-Like Assistant Iris Gets Integrated With Activity-Tracker Friday', 'Dexetra, the company behind&nbsp;the Siri-like Android app Iris&nbsp;and the activity-tracking app Friday, is merging the two apps to work together more seamlessly on Android devices. Giving Siri a run for its money, Iris users can now search the data archive generated by the Friday app, following the recent updates to these mobile applications rolled out over the Thanksgiving holiday here in the U.S.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(189, 'A Glimpse Of The Apocalypse: Walmart Customers Fight Over Phones On Black Friday', 'On Black Friday, discounted boxed smartphones seem to be the equivalent of dry foods in a post-apocalyptic movie. This video, first spotted by the Telegraph, shows customers of a Walmart in Moultrie, Georgia duking it out over discounted pay-as-you-go smartphones.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(190, '“In the Studio,” Gyft’s Vinny Lingham Presents A Brilliantly Dead-Simple Idea For Holiday Gift-Giving', '&quot;In the Studio&quot; powers through Black Friday by welcoming a serial entrepreneur, originally from South Africa, who has now started his third company out of Palo Alto and timed his moves perfectly for recent advances in mobile hardware and the holiday season. Vinny Lingham, the CEO and co-founder of Gyft, has devised a brilliantly dead-simple idea to attack the growing gift card market and enable both the purchasing of and redemption of cards through mobile phones. With Gyft, consumers can give, receive, and use gift cards from scores of other merchants with a simple mobile application and a few touches. A step further, Gyft had the foresight to realize the possibilities with iOS 6 and Passbook, making their offering fully-integrated with Passbook cards. (You can check out the app here, as well as their innovative incentive system to encourage signups. Gyft was also covered earlier in the year by TechCrunch''s Sarah Perez after integrating with Passbook.)', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(191, 'Nokia’s Mobile Imaging And Camera Chief Leaving The Company November 30', 'Nokia has recently made efforts to distinguish its smartphones with advanced photographic capabilities, introducing the PureView 808 with a 41MP rear camera. Now, Nokia''s long-time head of imaging and photography Damian Dinning, who has been with the company since 2004, is confirmed to be departing as of November 30. Dinning was also said to have had a key role in Windows Phone imaging software.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(192, '“In the Studio,” Sifteo’s David Merrill Unveils “Sifteo Cubes” To The World', '&quot;In the Studio&quot; presents the first of two special holiday segments this Thanksgiving holiday by welcoming one of the co-founders of one of my personal favorite startup companies. Almost five years ago, Sifteo''s David Merrill and his cofounder, Jeevan Kalanathi, embarked on a long journey that would take them from MIT''s Media Lab to San Francisco''s Dogpatch neighborhood, to blazing a trail for other venture-backed hardware startups during a time when traditional venture capital was more reluctant to deploy funds in this space. The result, after years and years of hard work, persistance, and learnings, is the Sifteo Cube, a set of small siftable computerized tiles with preloaded games for kids to play with and learn about math, spacial recognition, and most importantly, tactile play with real objects.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(193, 'ABI: Cumulative Mobile App Revenues To Exceed $30B By End Of 2012, Nearly Double 2011 Figure; Now “Major Digital Industry”', 'How much money have mobile apps made? By the end of the year the cumulative revenues of the global mobile app market will have exceeded $30 billion, says analyst ABI Research. This figure includes money from pay-per-download apps, in-app purchases, subscriptions and in-app advertisements -- the whole app kit-and-caboodle if you will.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(194, 'Google’s Nexus 4 Coming To Three In The UK December 13 On Contract And Pre-Paid Plans', 'Google is bringing the Nexus 4 to another carrier in the UK, expanding availability to Three after debuting the device with O2 as its initial partner in that country. The Android 4.2-powered smartphone will be available starting December 13 on Three, either on a &pound;35 per month contract with an upfront cost of just &pound;29 on the One Plan, or pre-paid for &pound;399.99.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(195, 'Apple Reportedly Changes Battery Suppliers As Samsung Walks', 'Apple has reportedly changed its battery suppliers for the iPad and MacBook lines to Amperex Technology Limited and Tianjin Lishen Battery, both Chinese firms, after Samsung SDI (Samsung''s battery-producing subsidiary) stopped supplying Apple, the China Business News reports. If accurate, this would be just the latest in a series of rifts between the two companies.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(196, 'How To Enable 4G LTE On The Google Nexus 4', 'Reports surfaced this morning that the Nexus 4, Google''s latest flagship Android smartphone, supports LTE via a relatively easy software hack. After testing, it turns out that''s definitely true, so I''ll show you exactly how to enable it on your device. Fair warning: the Nexus 4 only supports LTE on the AWS band (1700 or 2100MHz), which is currently used for LTE networks in Canada and on T-Mobile.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(197, 'Report: LTE Lives In Google and LG’s Nexus 4! (At Least In Canada)', 'Google''s Nexus 4 smartphones have been selling like hotcakes, despite one big criticism being that the device&nbsp;does not support super-fast wireless broadband on LTE networks. But a video has emerged that apparently refutes that fact: You can turn on LTE on the device, by way of a simple shortcode that you enter on the dial pad.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(198, 'God Complex: Peter Molyneux Kicks Off First Kickstarter With Project GODUS — A Grand Plan To “Recreate The Entire God Game Genre”', 'Legendary game designer Peter Molyneux has kicked off his first Kickstarter campaign -- with project GODUS, &quot;an innovative reinvention of Populous&quot;. For GODUS Molyneux is continuing to work with 22can, the Guildford-based games developer which built his first mobile game Curiosity, and talks about wanting to &quot;recreate the entire god game genre&quot;.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(199, 'Follow The Money: Why Priceline Bought Kayak', 'Last week, Priceline.com acquired Kayak for $1.8 billion. That''s more than twice  what Google paid for ITA back in 2010. Why did Priceline make this move? And why now? Priceline acquired Kayak for $40 a share, about $500 million in cash, $1 billion in Priceline equity, and about $300 million in stock options. Kayak got a premium price -- a 29 percent premium, to be exact -- from Priceline considering that, on the day of its acquisition, Kayak closed at roughly $31 a share. That''s about 54 percent higher than where Kayak was on IPO day, at $26 a share. This no doubt added to Priceline''s impression that Kayak was on the way up and that it was worth paying the big bucks to save it from having to deal with a strong competitor down the road.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(200, 'The Electree+, A Solar-Powered, Induction Charging Bonsai Tree Hits Kickstarter', 'Induction charging seems ready for its time in the spotlight, with the Nexus 4, Droid DNA and Lumia 920 all shipping with wireless charging based on the Qi standard built-in. Now a concept design that offers solar-powered wireless charging cleverly hidden inside a futuristic looking bonzai tree hopes to become a reality with the help of Kickstarter.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(201, 'IBM: Thanksgiving Sales Data Shows Mobile Commerce Jumping, iPhone/iPad Driving over 20% Of Traffic, Social Nets Only 0.2%', 'ComScore yesterday predicted that e-commerce sales would jump by 14% this holiday season compared to last year, to $42 billion, and some numbers just out from IBM''s&nbsp;Benchmark service&nbsp;--&nbsp;an ongoing measurement that covers some 500 of the largest online retailers in the U.S. -- indicate that consumers are getting a head start today. Online sales, it says, are already up by 14.3% on last year, with the average order at $132.57. IBM also highlighted a particularly strong showing in mobile commerce. [updated figures below]', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(202, 'A Guide For Mobile Game Developers To Survive The Holiday Season Rush', '<b>Editor''s note:</b> \n<em>Nate Barker&nbsp;is publisher relations manager and Jamie Evans is advertising account manager at Chartboost.</em> Here are a few things you need to know to stay calm, cool and collected this holiday season, so you can maximize the effectiveness of your mobile marketing budgets.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(203, 'Woz With A Coz: The 99c iOS Game That Puts An 8-Bit, Gun-Toting Woz In Your Pocket, With Danny Trejo As His Fearsome Sidekick', 'The life and times of Steve Wozniak are awesome enough already. But now Woz can add ''becoming an in-game character alongside a badass movie star'' to his achievements -- having been rendered in pixel-form for a retro 8-bit style iOS platform game called ''Vengeance: Woz with a Coz''. The legendary Apple co-founder also lends his voice to the game and helped write the script.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(204, 'Hands On With The Verizon FiOS Mobile App', 'Verizon updated its&nbsp;FiOS Mobile application for iPad, which now streams 75 channels of live TV. Unlike some of the mobile experiences Verizon has released in the past, the new application doesn''t require you to install software on your Mac or PC to act as the intermediary - instead, everything streams directly from the Verizon router in your home.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(205, 'Hours After Google Begins Referring Nexus 4 Shoppers To T-Mobile, T-Mobile Is Sold Out', 'Google began directing customers looking for the Nexus 4 in the U.S. to T-Mobile, since Google itself hasn''t had stock for quite a while now. T-Mobile charges $199 on a 2-year contract, after a $50 mail-in rebate. That''s not nearly as good a deal as the $300 Google was charging off-contract, but T-Mobile is now sold out only hours after Google started pointing shoppers in T-Mobile''s direction.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16'),
(206, 'Mobile App Devs Give Out Black Friday Deals, Too: Sourcebits Defers 50% Payment Until You Raise Your Next Round', 'Black Friday is best known as the day that consumers are bombarded with deals to kick off the holiday sales rush. Now Sourcebits, a mobile app development company,&nbsp;is hoping to use some of that mojo to drum up sales in its B2B business, too. From tomorrow, the&nbsp;Sequoia and IDG-backed&nbsp;startup will offer companies a 50-50 deal on new apps: Sourcebits asks for 50% of the cost of the app design and development, and then the clients only pay the other 50% when (and if) they next raise a round of funding.', 10, 1, 1, '2012-11-25 08:20:16', 1, '2012-11-25 02:50:16');

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

--
-- Dumping data for table `articleinterest`
--

INSERT INTO `articleinterest` (`articleid`, `interestid`) VALUES
(3, 1),
(6, 1),
(7, 1),
(8, 1),
(9, 1);

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

--
-- Dumping data for table `articlelinks`
--

INSERT INTO `articlelinks` (`articleid`, `articleurl`, `sourceid`) VALUES
(3, 'http://www.techcrunch.com', 4),
(6, 'http://www.techcrunch.com', 6),
(7, 'http://www.javaranch.com', 4),
(8, 'http://www.techcrunch.com', 4),
(9, 'http://www.techcrunch.com', 4),
(187, 'http://techcrunch.com/?p=697420', 6),
(188, 'http://techcrunch.com/?p=704920', 6),
(189, 'http://techcrunch.com/?p=704919', 6),
(190, 'http://techcrunch.com/?p=704512', 6),
(191, 'http://techcrunch.com/?p=704731', 6),
(192, 'http://techcrunch.com/?p=704481', 6),
(193, 'http://techcrunch.com/?p=704680', 6),
(194, 'http://techcrunch.com/?p=704676', 6),
(195, 'http://techcrunch.com/?p=704648', 6),
(196, 'http://techcrunch.com/?p=704631', 6),
(197, 'http://techcrunch.com/?p=704603', 6),
(198, 'http://techcrunch.com/?p=704585', 6),
(199, 'http://techcrunch.com/?p=701771', 6),
(200, 'http://techcrunch.com/?p=704535', 6),
(201, 'http://techcrunch.com/?p=704468', 6),
(202, 'http://techcrunch.com/?p=703047', 6),
(203, 'http://techcrunch.com/?p=704371', 6),
(204, 'http://techcrunch.com/?p=704382', 6),
(205, 'http://techcrunch.com/?p=704321', 6),
(206, 'http://techcrunch.com/?p=704306', 6);

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

--
-- Dumping data for table `articletag`
--

INSERT INTO `articletag` (`articleid`, `tagid`, `timestamp`) VALUES
(90, 32, '2012-11-25 00:29:56'),
(90, 34, '2012-11-25 00:29:56'),
(156, 36, '2012-11-25 00:46:33'),
(188, 36, '2012-11-25 01:01:02'),
(193, 36, '2012-11-25 01:01:06'),
(198, 36, '2012-11-25 01:01:09'),
(203, 36, '2012-11-25 01:01:14'),
(204, 36, '2012-11-25 01:01:15'),
(199, 37, '2012-11-25 01:01:12'),
(192, 38, '2012-11-25 01:01:05'),
(190, 44, '2012-11-25 01:01:03'),
(193, 44, '2012-11-25 01:01:05'),
(201, 44, '2012-11-25 01:01:13'),
(206, 49, '2012-11-25 01:01:17'),
(3, 53, '2012-11-07 04:49:37'),
(6, 53, '2012-11-25 07:40:39'),
(3, 54, '2012-11-07 04:49:36'),
(6, 54, '2012-11-25 07:40:39'),
(190, 63, '2012-11-25 01:01:04'),
(192, 63, '2012-11-25 01:01:05'),
(204, 63, '2012-11-25 01:01:15'),
(187, 64, '2012-11-25 01:01:01'),
(202, 64, '2012-11-25 01:01:14'),
(204, 64, '2012-11-25 01:01:16'),
(190, 65, '2012-11-25 01:01:03'),
(201, 65, '2012-11-25 01:01:13'),
(187, 76, '2012-11-25 01:01:01'),
(188, 76, '2012-11-25 01:01:02'),
(189, 76, '2012-11-25 01:01:03'),
(191, 76, '2012-11-25 01:01:05'),
(193, 76, '2012-11-25 01:01:05'),
(194, 76, '2012-11-25 01:01:06'),
(195, 76, '2012-11-25 01:01:07'),
(196, 76, '2012-11-25 01:01:07'),
(197, 76, '2012-11-25 01:01:08'),
(198, 76, '2012-11-25 01:01:09'),
(199, 76, '2012-11-25 01:01:10'),
(200, 76, '2012-11-25 01:01:12'),
(201, 76, '2012-11-25 01:01:13'),
(202, 76, '2012-11-25 01:01:14'),
(203, 76, '2012-11-25 01:01:14'),
(204, 76, '2012-11-25 01:01:15'),
(205, 76, '2012-11-25 01:01:16'),
(206, 76, '2012-11-25 01:01:17'),
(187, 79, '2012-11-25 01:01:01'),
(188, 79, '2012-11-25 01:01:02'),
(189, 79, '2012-11-25 01:01:03'),
(190, 79, '2012-11-25 01:01:04'),
(191, 79, '2012-11-25 01:01:05'),
(192, 79, '2012-11-25 01:01:05'),
(193, 79, '2012-11-25 01:01:06'),
(194, 79, '2012-11-25 01:01:06'),
(195, 79, '2012-11-25 01:01:07'),
(196, 79, '2012-11-25 01:01:08'),
(197, 79, '2012-11-25 01:01:08'),
(198, 79, '2012-11-25 01:01:09'),
(199, 79, '2012-11-25 01:01:12'),
(200, 79, '2012-11-25 01:01:13'),
(201, 79, '2012-11-25 01:01:13'),
(202, 79, '2012-11-25 01:01:14'),
(203, 79, '2012-11-25 01:01:15'),
(204, 79, '2012-11-25 01:01:15'),
(205, 79, '2012-11-25 01:01:16'),
(206, 79, '2012-11-25 01:01:17'),
(191, 81, '2012-11-25 01:01:05'),
(194, 81, '2012-11-25 01:01:07'),
(195, 81, '2012-11-25 01:01:07'),
(196, 81, '2012-11-25 01:01:08'),
(197, 81, '2012-11-25 01:01:09'),
(200, 81, '2012-11-25 01:01:13'),
(205, 81, '2012-11-25 01:01:16'),
(195, 84, '2012-11-25 01:01:07'),
(188, 86, '2012-11-25 01:01:02'),
(190, 86, '2012-11-25 01:01:03'),
(192, 86, '2012-11-25 01:01:05'),
(199, 86, '2012-11-25 01:01:12'),
(206, 86, '2012-11-25 01:01:17'),
(188, 91, '2012-11-25 01:01:02'),
(3, 92, '2012-11-07 04:49:36'),
(6, 92, '2012-11-25 07:40:39'),
(7, 92, '2012-11-25 07:43:41'),
(8, 92, '2012-11-25 07:43:41'),
(9, 92, '2012-11-25 07:43:41'),
(194, 92, '2012-11-25 01:01:06'),
(196, 92, '2012-11-25 01:01:07'),
(197, 92, '2012-11-25 01:01:08'),
(205, 92, '2012-11-25 01:01:16'),
(195, 93, '2012-11-25 01:01:07'),
(194, 98, '2012-11-25 01:01:06'),
(196, 98, '2012-11-25 01:01:08'),
(197, 98, '2012-11-25 01:01:08'),
(205, 98, '2012-11-25 01:01:16'),
(196, 100, '2012-11-25 01:01:08'),
(197, 100, '2012-11-25 01:01:08'),
(205, 100, '2012-11-25 01:01:16'),
(3, 102, '2012-11-07 05:41:03'),
(199, 102, '2012-11-25 01:01:11'),
(3, 103, '2012-11-07 04:49:36'),
(6, 103, '2012-11-25 07:40:39'),
(7, 103, '2012-11-25 07:43:41'),
(8, 103, '2012-11-25 07:43:41'),
(9, 103, '2012-11-25 07:43:41'),
(187, 104, '2012-11-25 01:01:01'),
(188, 105, '2012-11-25 01:01:02'),
(188, 106, '2012-11-25 01:01:02'),
(188, 107, '2012-11-25 01:01:02'),
(188, 108, '2012-11-25 01:01:02'),
(189, 109, '2012-11-25 01:01:03'),
(201, 109, '2012-11-25 01:01:13'),
(189, 110, '2012-11-25 01:01:03'),
(189, 111, '2012-11-25 01:01:03'),
(189, 112, '2012-11-25 01:01:03'),
(191, 113, '2012-11-25 01:01:04'),
(191, 114, '2012-11-25 01:01:04'),
(191, 115, '2012-11-25 01:01:04'),
(191, 116, '2012-11-25 01:01:04'),
(191, 117, '2012-11-25 01:01:04'),
(191, 118, '2012-11-25 01:01:05'),
(192, 119, '2012-11-25 01:01:05'),
(193, 120, '2012-11-25 01:01:06'),
(206, 120, '2012-11-25 01:01:17'),
(194, 121, '2012-11-25 01:01:06'),
(194, 122, '2012-11-25 01:01:06'),
(194, 123, '2012-11-25 01:01:06'),
(198, 123, '2012-11-25 01:01:09'),
(195, 124, '2012-11-25 01:01:07'),
(195, 125, '2012-11-25 01:01:07'),
(196, 126, '2012-11-25 01:01:08'),
(196, 127, '2012-11-25 01:01:08'),
(197, 127, '2012-11-25 01:01:08'),
(198, 128, '2012-11-25 01:01:09'),
(202, 128, '2012-11-25 01:01:14'),
(203, 128, '2012-11-25 01:01:14'),
(198, 129, '2012-11-25 01:01:09'),
(198, 130, '2012-11-25 01:01:09'),
(198, 131, '2012-11-25 01:01:09'),
(200, 131, '2012-11-25 01:01:12'),
(199, 132, '2012-11-25 01:01:10'),
(199, 133, '2012-11-25 01:01:10'),
(199, 134, '2012-11-25 01:01:10'),
(199, 135, '2012-11-25 01:01:10'),
(199, 136, '2012-11-25 01:01:10'),
(199, 137, '2012-11-25 01:01:11'),
(199, 138, '2012-11-25 01:01:11'),
(199, 139, '2012-11-25 01:01:11'),
(199, 140, '2012-11-25 01:01:12'),
(200, 141, '2012-11-25 01:01:12'),
(200, 142, '2012-11-25 01:01:12'),
(200, 143, '2012-11-25 01:01:12'),
(200, 144, '2012-11-25 01:01:13'),
(201, 145, '2012-11-25 01:01:13'),
(201, 146, '2012-11-25 01:01:13'),
(201, 147, '2012-11-25 01:01:13'),
(202, 148, '2012-11-25 01:01:14'),
(203, 149, '2012-11-25 01:01:14'),
(203, 150, '2012-11-25 01:01:14'),
(204, 151, '2012-11-25 01:01:15'),
(204, 152, '2012-11-25 01:01:15'),
(204, 153, '2012-11-25 01:01:15'),
(204, 154, '2012-11-25 01:01:15'),
(204, 155, '2012-11-25 01:01:16'),
(204, 156, '2012-11-25 01:01:16'),
(205, 157, '2012-11-25 01:01:16'),
(206, 158, '2012-11-25 01:01:17');

-- --------------------------------------------------------

--
-- Table structure for table `blockedusers`
--

CREATE TABLE IF NOT EXISTS `blockedusers` (
  `userid` int(11) NOT NULL DEFAULT '0',
  `blockerid` int(11) NOT NULL DEFAULT '0',
  `timestamp` datetime DEFAULT NULL,
  `reason` varchar(200) DEFAULT NULL,
  `isactive` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`userid`,`blockerid`),
  KEY `ubb_fk22` (`blockerid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `favourites`
--

CREATE TABLE IF NOT EXISTS `favourites` (
  `userid` int(11) NOT NULL,
  `articleid` int(11) NOT NULL,
  `readlater` tinyint(1) DEFAULT NULL,
  `isfav` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`userid`,`articleid`),
  KEY `userid` (`userid`,`articleid`),
  KEY `articleid` (`articleid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `favourites`
--

INSERT INTO `favourites` (`userid`, `articleid`, `readlater`, `isfav`) VALUES
(1, 3, 0, 0),
(1, 6, 1, 0),
(1, 7, 1, 0),
(1, 8, 0, 1),
(1, 9, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `interest`
--

CREATE TABLE IF NOT EXISTS `interest` (
  `interestid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(500) NOT NULL,
  `iconpath` varchar(200) NOT NULL DEFAULT 'icn_interest.png',
  `timestamp` datetime NOT NULL,
  PRIMARY KEY (`interestid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `interest`
--

INSERT INTO `interest` (`interestid`, `name`, `description`, `iconpath`, `timestamp`) VALUES
(1, 'java', 'javap', 'icn_interest.png', '2012-11-25 00:47:04'),
(2, 'sql', 'sql', 'icn_interest.png', '2012-10-31 23:21:02'),
(4, 'Mobile', '', 'icn_interest.png', '2012-11-25 00:51:41'),
(5, 'Web Design', '', 'icn_interest.png', '2012-11-25 00:53:38');

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

--
-- Dumping data for table `interestsources`
--

INSERT INTO `interestsources` (`interestid`, `sourceid`) VALUES
(1, 4),
(2, 1),
(4, 6),
(5, 5);

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `rolename` varchar(20) NOT NULL,
  `description` varchar(200) NOT NULL,
  PRIMARY KEY (`rolename`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`rolename`, `description`) VALUES
('admin', 'admin role'),
('client', 'client role'),
('manager-script', 'this is required role for tomcat to run');

-- --------------------------------------------------------

--
-- Table structure for table `source`
--

CREATE TABLE IF NOT EXISTS `source` (
  `sourceid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `url` varchar(200) NOT NULL,
  `feedurl` varchar(200) NOT NULL,
  `icon` varchar(200) NOT NULL DEFAULT 'icn_source.png',
  `isactive` tinyint(1) NOT NULL,
  PRIMARY KEY (`sourceid`),
  UNIQUE KEY `url_unq` (`feedurl`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `source`
--

INSERT INTO `source` (`sourceid`, `name`, `url`, `feedurl`, `icon`, `isactive`) VALUES
(1, 'sql', 'www.sql.com', 'www.sql.com/feeds', 'icn_source.png', 0),
(4, 'javaranch', 'www.javaranch.com', 'feedurl1', 'icn_source.png', 1),
(5, 'Smashing Magazine', 'www.smashingmagazine.com', 'http://rss1.smashingmagazine.com/feed/', 'icn_source.png', 1),
(6, 'Techcrunch', 'www.techcrunch.com', 'http://feeds.feedburner.com/Mobilecrunch', 'icn_source.png', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tag`
--

CREATE TABLE IF NOT EXISTS `tag` (
  `tagid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `icon` varchar(200) NOT NULL DEFAULT 'icn_tags.png',
  `description` varchar(500) NOT NULL,
  PRIMARY KEY (`tagid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=159 ;

--
-- Dumping data for table `tag`
--

INSERT INTO `tag` (`tagid`, `name`, `icon`, `description`) VALUES
(32, 'operating system', 'icn_tags.png', ''),
(33, 'tab', 'icn_tags.png', ''),
(34, 'tablet', 'icn_tags.png', ''),
(35, 'venture', 'icn_tags.png', ''),
(36, 'apps', 'icn_tags.png', ''),
(37, 'fundings & exits', 'icn_tags.png', ''),
(38, 'venture capital', 'icn_tags.png', ''),
(39, 'samsung galaxy note ii', 'icn_tags.png', ''),
(40, 'phablets', 'icn_tags.png', ''),
(41, 'nabi 2', 'icn_tags.png', ''),
(42, 'kddi', 'icn_tags.png', ''),
(43, 'fuhu', 'icn_tags.png', ''),
(44, 'ecommerce', 'icn_tags.png', ''),
(45, 'app search', 'icn_tags.png', ''),
(46, 'getjar gold', 'icn_tags.png', ''),
(47, 'getjar', 'icn_tags.png', ''),
(48, 'discovery', 'icn_tags.png', ''),
(49, 'developers', 'icn_tags.png', ''),
(50, 'app-store', 'icn_tags.png', ''),
(51, 'app stores', 'icn_tags.png', ''),
(52, 'monetization', 'icn_tags.png', ''),
(53, 'Java Evolution', 'icn_tags.png', ''),
(54, 'Java Server Programming', 'icn_tags.png', ''),
(55, 'social media', 'icn_tags.png', ''),
(56, 'local', 'icn_tags.png', ''),
(57, 'social media management', 'icn_tags.png', ''),
(58, 'mobile marketing', 'icn_tags.png', ''),
(59, 'postling', 'icn_tags.png', ''),
(60, 'localvox', 'icn_tags.png', ''),
(61, 'local marketing', 'icn_tags.png', ''),
(62, 'marketing', 'icn_tags.png', ''),
(63, 'video', 'icn_tags.png', ''),
(64, 'opinion', 'icn_tags.png', ''),
(65, 'social', 'icn_tags.png', ''),
(66, 'traffic', 'icn_tags.png', ''),
(67, 'mobile web', 'icn_tags.png', ''),
(68, 'iphone 5', 'icn_tags.png', ''),
(69, 'blackberry', 'icn_tags.png', ''),
(70, 'rim', 'icn_tags.png', ''),
(71, 'iphone', 'icn_tags.png', ''),
(72, 'government', 'icn_tags.png', ''),
(73, 'windows phone 8', 'icn_tags.png', ''),
(74, '4th gen ipad', 'icn_tags.png', ''),
(75, 'ipad', 'icn_tags.png', ''),
(76, 'tc', 'icn_tags.png', ''),
(77, 'retina', 'icn_tags.png', ''),
(78, 'ipad mini', 'icn_tags.png', ''),
(79, 'mobile', 'icn_tags.png', ''),
(80, 'display', 'icn_tags.png', ''),
(81, 'gadgets', 'icn_tags.png', ''),
(82, 'ios', 'icn_tags.png', ''),
(83, 'ios 6', 'icn_tags.png', ''),
(84, 'apple', 'icn_tags.png', ''),
(85, 'nexage', 'icn_tags.png', ''),
(86, 'startups', 'icn_tags.png', ''),
(87, 'advertising', 'icn_tags.png', ''),
(88, 'retail', 'icn_tags.png', ''),
(89, 'customer service', 'icn_tags.png', ''),
(90, 'business', 'icn_tags.png', ''),
(91, 'android', 'icn_tags.png', ''),
(92, 'programming using java', 'icn_tags.png', ''),
(93, 'samsung', 'icn_tags.png', ''),
(94, 'market share', 'icn_tags.png', ''),
(95, 'report', 'icn_tags.png', ''),
(96, 'htc', 'icn_tags.png', ''),
(97, 'facebook', 'icn_tags.png', ''),
(98, 'nexus', 'icn_tags.png', ''),
(99, 'android 4.2', 'icn_tags.png', ''),
(100, 'lg', 'icn_tags.png', ''),
(101, 'jelly bean', 'icn_tags.png', ''),
(102, 'reviews', 'icn_tags.png', ''),
(103, 'java', 'icn_tags.png', ''),
(104, 'mobile payments', 'icn_tags.png', ''),
(105, 'iris', 'icn_tags.png', ''),
(106, 'virtual assistant', 'icn_tags.png', ''),
(107, 'friday', 'icn_tags.png', ''),
(108, 'personal assistant', 'icn_tags.png', ''),
(109, 'black friday', 'icn_tags.png', ''),
(110, 'phones', 'icn_tags.png', ''),
(111, 'walmart', 'icn_tags.png', ''),
(112, 'wtf', 'icn_tags.png', ''),
(113, 'lumia 920', 'icn_tags.png', ''),
(114, 'camera+', 'icn_tags.png', ''),
(115, 'pureview', 'icn_tags.png', ''),
(116, 'imaging', 'icn_tags.png', ''),
(117, 'nokia', 'icn_tags.png', ''),
(118, 'pureview 808', 'icn_tags.png', ''),
(119, 'education', 'icn_tags.png', ''),
(120, 'mobile apps', 'icn_tags.png', ''),
(121, 'three', 'icn_tags.png', ''),
(122, 'uk', 'icn_tags.png', ''),
(123, 'europe', 'icn_tags.png', ''),
(124, 'batteries', 'icn_tags.png', ''),
(125, 'supply relationship', 'icn_tags.png', ''),
(126, 'smartphone', 'icn_tags.png', ''),
(127, 'lte', 'icn_tags.png', ''),
(128, 'gaming', 'icn_tags.png', ''),
(129, 'peter molyneux', 'icn_tags.png', ''),
(130, 'godus', 'icn_tags.png', ''),
(131, 'kickstarter', 'icn_tags.png', ''),
(132, 'travel', 'icn_tags.png', ''),
(133, 'media', 'icn_tags.png', ''),
(134, 'hotel', 'icn_tags.png', ''),
(135, 'booking', 'icn_tags.png', ''),
(136, 'priceline', 'icn_tags.png', ''),
(137, 'airlines', 'icn_tags.png', ''),
(138, 'search', 'icn_tags.png', ''),
(139, 'airfare', 'icn_tags.png', ''),
(140, 'kayak', 'icn_tags.png', ''),
(141, 'qi', 'icn_tags.png', ''),
(142, 'solar power', 'icn_tags.png', ''),
(143, 'greentech', 'icn_tags.png', ''),
(144, 'wireless charging', 'icn_tags.png', ''),
(145, 'holiday shopping', 'icn_tags.png', ''),
(146, 'thanksgiving', 'icn_tags.png', ''),
(147, 'ibm', 'icn_tags.png', ''),
(148, 'game development', 'icn_tags.png', ''),
(149, 'woz', 'icn_tags.png', ''),
(150, 'steve wozniak', 'icn_tags.png', ''),
(151, 'verizon fios', 'icn_tags.png', ''),
(152, 'tv', 'icn_tags.png', ''),
(153, 'television', 'icn_tags.png', ''),
(154, 'fios', 'icn_tags.png', ''),
(155, 'streaming', 'icn_tags.png', ''),
(156, 'verizon', 'icn_tags.png', ''),
(157, 't-mobile', 'icn_tags.png', ''),
(158, 'sourcebits', 'icn_tags.png', '');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `firstname` varchar(20) NOT NULL,
  `lastname` varchar(20) NOT NULL,
  `dob` date NOT NULL,
  `state` int(11) DEFAULT NULL,
  `city` int(11) DEFAULT NULL,
  `country` int(11) DEFAULT NULL,
  `isactive` tinyint(1) NOT NULL,
  `timeofregistration` datetime NOT NULL,
  `image` varchar(255) DEFAULT 'default.jpg',
  PRIMARY KEY (`userid`),
  UNIQUE KEY `username_unq` (`username`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `username_2` (`username`),
  UNIQUE KEY `username_3` (`username`),
  UNIQUE KEY `username_4` (`username`),
  UNIQUE KEY `username_5` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userid`, `username`, `password`, `email`, `firstname`, `lastname`, `dob`, `state`, `city`, `country`, `isactive`, `timeofregistration`, `image`) VALUES
(1, 'ankur.trapasiya', 'd7afde3e7059cd0a0fe09eec4b0008cd', 'test123@gmail.com', 'ankur', 'Trapasiya', '2012-10-31', 1, 1, 1, 1, '2012-10-31 00:00:00', 'default.jpg'),
(2, 'anil.trapasiya', '0cc175b9c0f1b6a831c399e269772661', 'anil.trapasiya@gmail', 'anil', 'Trapasiya', '2012-10-31', NULL, NULL, 0, 1, '2012-10-31 23:21:02', 'default.jpg'),
(4, 'sonu.trapasiya', '371ab955fdc11c44c980779c3135b155', 'sonu.trapasiya@gmail', 'sonu', 'Trapasiya', '2012-11-04', NULL, NULL, 0, 1, '2012-11-04 16:47:25', 'default.jpg'),
(8, 'at', '81dc9bdb52d04dc20036dbd8313ed055', 'ankur.trapasiya1', 'divu', 'a', '2012-11-01', NULL, NULL, 0, 1, '2012-11-05 00:00:00', 'default.jpg'),
(9, 'ay1', '81dc9bdb52d04dc20036dbd8313ed055', 'ankur.trapasiya@gmail.com', 'lalu', 'a', '2012-11-08', NULL, NULL, 0, 1, '2012-11-05 00:00:00', 'default.jpg'),
(10, 'harmish', '1630937c3d00b4f4b153599d93469963', 'harmishrulz@live.in', 'harmish', 'shah', '2012-11-02', NULL, NULL, 0, 1, '2012-11-10 00:00:00', NULL),
(11, '201112018', '4f03a3d7d3dffa764d27606ff3773311', 'vikram.singh757@gmail.com', 'Vikram', 'singh', '2003-11-04', NULL, NULL, 0, 1, '2012-11-10 00:00:00', NULL),
(12, 'ds', '1630937c3d00b4f4b153599d93469963', 'h@li.vin', 'harsh', 'shah', '2012-11-07', NULL, NULL, 0, 1, '2012-11-10 00:00:00', NULL),
(13, 'hkshah', 'd6a95d05de5c853e7a18367c517b425d', 'har@gmail.com', 'harmish', 'shah', '2012-11-08', NULL, NULL, 0, 1, '2012-11-10 00:00:00', NULL),
(14, 'admin', '0cc175b9c0f1b6a831c399e269772661', 'admin@gmail.com', 'admin', 'admin', '1990-02-03', NULL, NULL, 0, 1, '2012-11-16 01:00:00', 'default.jpg'),
(20, 'test', '098f6bcd4621d373cade4e832627b4f6', 'test@test.com', 'test', 'test', '2012-11-10', 0, 0, 2, 1, '2012-11-24 00:00:00', NULL);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `useractivation`
--

INSERT INTO `useractivation` (`id`, `userid`, `token`, `timestamp`, `isactive`) VALUES
(1, 9, '3571f45618ca26d8072369b8e7215ad6', '2012-11-05 19:11:14', 0),
(2, 10, 'eeac6231633e8f5564c8dd9b60533e23', '2012-11-10 13:05:39', 0),
(3, 11, 'a855b31868916d0291a2a6bc799f8fe7', '2012-11-10 13:41:21', 1),
(4, 12, '109fb9a13c67acdff1f83664b029798c', '2012-11-10 15:00:18', 0),
(5, 13, 'aa21b4d449c6d6e2985e1882b1c8539b', '2012-11-10 15:04:04', 0),
(11, 20, '44b85c98e94039c8a0a015f6d3a3449e', '2012-11-24 20:35:38', 0);

-- --------------------------------------------------------

--
-- Table structure for table `usergraph`
--

CREATE TABLE IF NOT EXISTS `usergraph` (
  `friendid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `isnotified` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`friendid`,`userid`),
  KEY `userid` (`userid`),
  KEY `userid_2` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usergraph`
--

INSERT INTO `usergraph` (`friendid`, `userid`, `isnotified`) VALUES
(1, 2, 1),
(1, 9, 1),
(2, 1, 1),
(9, 1, 1);

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

--
-- Dumping data for table `userhistory`
--

INSERT INTO `userhistory` (`userid`, `articleid`, `timestamp`, `upvote`, `downvote`) VALUES
(20, 3, '2012-11-25 08:18:56', 1, 0),
(20, 7, '2012-11-25 08:21:03', 1, 0),
(20, 8, '2012-11-25 09:56:23', 1, 0);

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

--
-- Dumping data for table `userinterest`
--

INSERT INTO `userinterest` (`userid`, `interestid`) VALUES
(1, 1),
(2, 1),
(8, 1),
(10, 1),
(11, 1),
(20, 1),
(1, 2),
(2, 2),
(10, 2),
(20, 2),
(1, 4),
(1, 5);

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

--
-- Dumping data for table `userrole`
--

INSERT INTO `userrole` (`rolename`, `userid`, `username`) VALUES
('admin', 14, 'admin'),
('manager-script', 14, 'admin');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `usersuggestions`
--

INSERT INTO `usersuggestions` (`id`, `userid`, `friendid`, `articleid`, `isviewed`, `timestamp`) VALUES
(1, 1, 2, 2, 1, '2012-11-25 00:00:00');

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
  ADD CONSTRAINT `ubb_fk22` FOREIGN KEY (`blockerid`) REFERENCES `user` (`userid`),
  ADD CONSTRAINT `ubu_fk11` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`);

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
  ADD CONSTRAINT `userhistory_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`),
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
