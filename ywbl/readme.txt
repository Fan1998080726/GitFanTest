20191019 处理竣工备案号的sql  begin
select t.*, t.rowid from tbprojectfinishmanage t where t.type='2' and t.finishmanagenum is null and t.state='已办结'

select t.*, t.rowid from tbprojectfinishmanage t where t.prjnum like '%2301021608020101%'   order by t.builderlicencenum,t.lastupdatetime,t.finishmanagenum

   delete  from tbprojectfinishmanage t where  t.id='2019080195931511601879456'
   
   
  select t.*, t.rowid from tbprojectfinishmanage t where t.prjnum like '%2301021608020101%' and t.enddate is null order by t.builderlicencenum,t.lastupdatetime
  
   delete   from tbprojectfinishmanage t where t.prjnum like '%230102160802010110101%' and t.enddate is null 
 
 select t.*, t.rowid from tbprojectfinishmanage t where t.prjnum like '%2301021608020101%'   order by t.finishmanagenum

20191019 处理竣工备案号的sql  end 

20171001
 sql="select *  from (select * from TBProjectInfo "+str+" order by date_ desc,id desc) " +
        		" where rownum<=15 and id not in" +
        		" ( select id from (select * from TBProjectInfo "+str+" order by date_ desc,id desc) " +
        				"where ROWNUM<="+((pagination.getCurrentPageNo()/15)*15)+")";
备注：当date_一样时，排序有问题，需要增加id，保证唯一性


20170304
UndertakerAuditPage.jsp页面，在title处引入相关文件后，承办人、审核人、审批人三个日期控件同时在页面体现，有在提交时，没有反应，需要增加一个判断，保证一个办件人只有一个日期控件。

20170227当连接vpn时，用曹继平账号登录系统，打印通知单，不好使，必须断开vpn才可以，切记切记！！！

20170218 能否通过程序抓取客户端电脑访问系统的图片，待研究。
设计处，企业上报、管理部门审核业务流程设计
1、五个主城区用户，在项目报建的时候就创建用户，包括项目报建、施工图审查、施工许可三个模块。
2、设计处窗口创建的用户应该只包括五个主城区之外的用户。
施工图审查 审批日期在前、审查完成日期在后


/* window.screen.width
window.screen.height */

20150607 检查大队录入检查日报，需要选择企业 改功能没有完成。
20150612企业名称变更后，也应该能查询到该企业。
20150613需要把tablegcjg表中的施工图审查中的项目名称改成工程名称（中文、英文）都需要改。
20150614
角色管理添加后没有显示，必须重新启动tomcat后，才有显示，有缓存问题。
缓存问题很严重，在部门管理里，增加完数据后，几秒钟内好使，过了一会，又回到没有增加数据之前的状态。
20150616
<%////System.out.println("测试..."); %>

查询分析有瑕疵
http://127.0.0.1/shigonggl/jsp/warningshow/warningShow.jsp 统计分析图形
单位工程质量监督登记表管理 增加类型 type_有bug
单位工程质量监督登记表 没有区分市政站和质监总站的数据。
http://192.168.110.99/gcjg/jsp/system/tBProjectInfo/aaa.jsp
监理合同管理/监理企业/房地产开发企业管理/哈尔滨市勘察设计企业信息管理/施工总包合同管理/专业分包合同管理/劳务分包合同管理
，不能及时更新。
2015-06-28
同时运行两个yu项目会出错，怀疑和session有关系，可能这个session是属于整个tomcat。
2015-08-18
现场监管模块，应该明确每条记录是哪个部门填写的！
已经办结的信息应该增加限制
20150906
每个模块都应该增加数据录入的系统时间
当前位置：施工图审查备案管理  应该增加“办结”
20150909
涉及到选择标段的，查看页中没有标段名称
25、勘察设计审图人员明细………….........质监站、市政站负责	28
26、 施工安全从业人员明细………….....................安全站负责	29

数据没有按某个项目读取
当前位置：手续填报数量统计分析管理   还有停工管理
检测机构管理   当前位置：检测机构人员管理   添加完数据后，没有添加、修改、删除按钮
用户密码管理
20150914
清欠办 增加一个模块，可以能及时看到哪些项目没有办理清欠证明

20160326
workspace字样的需要在上传服务器之前更新
 


20160425
CREATE SEQUENCE emp_sequence --序列名
INCREMENT BY 1 -- 每次加几个
START WITH 1 -- 从1开始计数
NOMAXVALUE -- 不设置最大值
NOCYCLE -- 一直累加，不循环
CACHE 10; 

EMP_PROID
EMP_SEQUENCE
EMP_LOGMANID


create or replace view duildandprojectview as
select t.*,d.* from  duildsuperviseproject  t
LEFT OUTER JOIN projectenforceinspectcollect d on t.dusuprid=d.produsuprid



create or replace view vproject as
select c.*,a.op,a.od from ((select c.*,nvl(a.conotdusup,0) as conotdusup from((select c.*,nvl(a.countproLEAbook,0)as countproLEAbook from ((select c.*,nvl(a.countprochargeRAR,0)as countprochargeRAR from ((select c.*,nvl(a.countprolockoutian,0)as countprolockoutian from ((select c.*, nvl(a.countprotherpb,0)as countprotherpb from
((select c.*, nvl(a.countprosafetycase,0)as countprosafetycase from ((select c.*, nvl(a.countpromasscase,0)as countpromasscase from ((select c.dusuprgroup,a.* from((select a.*,nvl(b.countproconplalicense,0)as countproconplalicense from
((select a.*, nvl(b.countprocontsoa,0)as countprocontsoa from((select c.*, nvl(b.countprolandplanlicence,0)as countprolandplanlicence from (select i.*,nvl(o.countproconslicence,0)as countproconslicence from
((select s.dusuprarea, nvl(a.countprodusuprid,0)as countprodusuprid,s.sumprocoveredarea from (select dusuprarea, nvl(sum(procoveredarea),0)as sumprocoveredarea  from
(select t.*,d.* from  duildsuperviseproject  t LEFT OUTER JOIN projectenforceinspectcollect d on t.dusuprid=d.produsuprid) where dusuprgroup<>'第四组'
 group by dusuprarea,op,od order by op,od asc) s LEFT OUTER JOIN (select dusuprarea as 区丶县,count(*) as countprodusuprid from
(select q.*,z.* from(select a.* from projectenforceinspectcollect a
where a.produsuprid in (select d.produsuprid from  duildsuperviseproject  t
LEFT OUTER JOIN projectenforceinspectcollect d on t.dusuprid=d.produsuprid))z
LEFT OUTER JOIN duildsuperviseproject q on z.produsuprid=q.dusuprid) where dusuprgroup<>'第四组' group by dusuprarea,op,od order by op,od asc) a on s.dusuprarea=a.区丶县) i LEFT OUTER JOIN
(select dusuprarea as 区丶县,count(*) as countproconslicence from (select t.*,d.* from  duildsuperviseproject  t
LEFT OUTER JOIN projectenforceinspectcollect d on t.dusuprid=d.produsuprid) a
where a.proconslicence=0 and a.dusuprgroup<>'第四组' group by dusuprarea,op,od order by op,od asc) o on i.dusuprarea=o.区丶县)) c LEFT OUTER JOIN
(select dusuprarea as 区丶县,count(*) as countprolandplanlicence from (select t.*,d.* from  duildsuperviseproject  t
LEFT OUTER JOIN projectenforceinspectcollect d on t.dusuprid=d.produsuprid) a where a.prolandplanlicence=0 and a.dusuprgroup<>'第四组' group by dusuprarea,op,od order by op,od asc) b on c.dusuprarea=b.区丶县)) a
LEFT OUTER JOIN
(select dusuprarea as 区丶县,count(*) as countprocontsoa from(select t.*,d.* from  duildsuperviseproject  t
LEFT OUTER JOIN projectenforceinspectcollect d on t.dusuprid=d.produsuprid) a where a.procontsoa=0  and a.dusuprgroup<>'第四组' group by dusuprarea,op,od order by op,od asc) b on a.dusuprarea=b.区丶县)) a
LEFT OUTER JOIN
(select dusuprarea as 区丶县,count(*) as countproconplalicense from (select t.*,d.* from  duildsuperviseproject  t
LEFT OUTER JOIN projectenforceinspectcollect d on t.dusuprid=d.produsuprid) a where a.proconplalicense=0 and a.dusuprgroup<>'第四组' group by dusuprarea,op,od order by op,od asc) b on a.dusuprarea=b.区丶县)) a
LEFT OUTER JOIN
(select t.dusuprgroup,t.dusuprarea,op,od from (select t.*,d.* from  duildsuperviseproject  t
LEFT OUTER JOIN projectenforceinspectcollect d on t.dusuprid=d.produsuprid)  t where  dusuprgroup<>'第四组' group by t.dusuprgroup,t.dusuprarea,op,od order by op,od asc) c on a.dusuprarea=c.dusuprarea)) c
LEFT OUTER JOIN
(select dusuprarea,count(promasscase)as countpromasscase from (select t.*,d.* from  duildsuperviseproject  t
LEFT OUTER JOIN projectenforceinspectcollect d on t.dusuprid=d.produsuprid) a
where a.promasscase is not null and a.dusuprgroup<>'第四组' group by dusuprarea,op,od order by op,od asc) a on c.dusuprarea=a.dusuprarea)) c
LEFT OUTER JOIN
(select dusuprarea,count(prosafetycase)as countprosafetycase from (select t.*,d.* from  duildsuperviseproject  t
LEFT OUTER JOIN projectenforceinspectcollect d on t.dusuprid=d.produsuprid) a
where a.prosafetycase is not null and a.dusuprgroup<>'第四组' group by dusuprarea,op,od order by op,od asc) a on c.dusuprarea=a.dusuprarea)) c
LEFT OUTER JOIN
(select dusuprarea,op,od,count(protherpb)as countprotherpb from (select t.*,d.* from  duildsuperviseproject  t
LEFT OUTER JOIN projectenforceinspectcollect d on t.dusuprid=d.produsuprid) a
where a.protherpb is not null and a.dusuprgroup<>'第四组' group by a.dusuprarea,op,od order by op,od asc) a on c.dusuprarea=a.dusuprarea)) c
LEFT OUTER JOIN
(select dusuprarea,op,od,count(prolockoutian) as countprolockoutian from (select t.*,d.* from  duildsuperviseproject  t
LEFT OUTER JOIN projectenforceinspectcollect d on t.dusuprid=d.produsuprid) a where a.prolockoutian=0 or a.prolockoutian is null and a.dusuprgroup<>'第四组' group by a.dusuprarea,op,od order by op,od asc) a
on c.dusuprarea=a.dusuprarea)) c
LEFT OUTER JOIN
(select dusuprarea,op,od,count(prochargeRAR) as countprochargeRAR from (select t.*,d.* from  duildsuperviseproject  t
LEFT OUTER JOIN projectenforceinspectcollect d on t.dusuprid=d.produsuprid) a where a.prochargeRAR=0 or a.prochargeRAR is null and a.dusuprgroup<>'第四组'  group by a.dusuprarea,op,od order by op,od asc) a
on c.dusuprarea=a.dusuprarea)) c
LEFT OUTER JOIN
(select dusuprarea,op,od ,count(proLEAbook) as countproLEAbook from (select t.*,d.* from  duildsuperviseproject  t
LEFT OUTER JOIN projectenforceinspectcollect d on t.dusuprid=d.produsuprid) a where a.proLEAbook=0 or a.proLEAbook is null and a.dusuprgroup<>'第四组' group by dusuprarea,op,od order by op,od asc) a on c.dusuprarea=a.dusuprarea)) c
LEFT OUTER JOIN
(select t.dusuprarea,count(t.dusuprid) as conotdusup from (select t.* from duildsuperviseproject t where t.dusuprid
in(select a.produsuprid from PROJECTENFORCEINSPECTCOLLECT a group by a.produsuprid)) t  where t.dusuprgroup<>'第四组' group by t.dusuprarea,op,od order by op,od asc) a on c.dusuprarea=a.dusuprarea)) c
LEFT OUTER JOIN
(select dusuprarea,od,op from duildsuperviseproject t where t.dusuprgroup<>'第四组' group by t.dusuprarea,op,od order by op,od asc) a on c.dusuprarea=a.dusuprarea







