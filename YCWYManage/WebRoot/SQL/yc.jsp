------------房屋表添加楼层字段、是否交房字段-------------
Alter table TB_Estate_House add storey int null, handIs int null
------------订单表添加修改说明字段-------------
Alter table TB_Estate_Order add up_Reason varchar(50) null

-----电梯费区间设置表-----
CREATE TABLE TB_Estate_DTFSection(
 Se_id int identity primary key,
 ts_id int NOT NULL,
 Es_id int NOT NULL,
 Ei_id int NOT NULL,
 se_qz int NOT NULL,
 se_hz int NOT NULL,
 Seprice money NOT NULL,
 remark varchar(20) NULL,
 create_time datetime NOT NULL,
 status int NOT NULL,
)
----------------运城物业版本菜单------------------
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('19', '山西运城物业版本服务系统', '0', 0, '0', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('1901', '系统管理', '0', 1, '19', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('190101', '机构管理', 'YCWYPage/BackPage/system/org.jsp', 2, '1901', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('190102', '操作员管理', 'YCWYPage/BackPage/system/operator.jsp', 2, '1901', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('190103', '业主登陆设置', 'YCWYPage/BackPage/system/signin.jsp', 2, '1901', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('190104', '修改密码', 'YCWYPage/BackPage/system/password.jsp', 2, '1901', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('190105', '系统日志', 'YCWYPage/BackPage/system/log.jsp', 2, '1901', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('1902', '小区管理', '0', 1, '19', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('190201', '新增小区', 'YCWYPage/BackPage/quarters/xqadd.jsp', 2, '1902', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('190202', '楼宇信息', 'YCWYPage/BackPage/quarters/ladd.jsp', 2, '1902', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('190203', '单元信息', 'YCWYPage/BackPage/quarters/dyadd.jsp', 2, '1902', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) values ('190204','房屋类型管理','YCWYPage/BackPage/quarters/hostType.jsp','2','1902','1');
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('190205', '房屋信息', 'YCWYPage/BackPage/quarters/fwmess.jsp', 2, '1902', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('190206', '批量导入房屋信息', 'YCWYPage/BackPage/quarters/fwaddpl.jsp', 2, '1902', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('1903', '计费管理', '0', 1, '19', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('190301', '物业费', 'YCWYPage/BackPage/calculate/WuYe_item.jsp', 2, '1903', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('190302', '供暖费', 'YCWYPage/BackPage/calculate/GongRe_item.jsp', 2, '1903', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('190303', '电梯费', 'YCWYPage/BackPage/calculate/DianTi_item.jsp', 2, '1903', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('190304', '车位费', 'YCWYPage/BackPage/calculate/Car_item.jsp', 2, '1903', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('190305', '水费', 'YCWYPage/BackPage/calculate/Water_item.jsp', 2, '1903', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('190306', '电费', 'YCWYPage/BackPage/calculate/Electric_item.jsp', 2, '1903', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('190307', '其他费设置', 'YCWYPage/BackPage/calculate/charge_item.jsp', 2, '1903', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('1904', '收费管理', '0', 1, '19', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('190401', '新增收费单', 'YCWYPage/BackPage/charge/charge_add.jsp', 2, '1904', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('190402', '收费单管理', 'YCWYPage/BackPage/charge/charge_select.jsp', 2, '1904', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('190403', '抄表录入', 'YCWYPage/BackPage/charge/mete_reading.jsp', 2, '1904', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('190404', '抄表查询', 'YCWYPage/BackPage/charge/mete_select.jsp', 2, '1904', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('190405', '批量导入', 'YCWYPage/BackPage/charge/gue_pl.jsp', 2, '1904', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('190406', '批量导入缴费查询', 'YCWYPage/BackPage/charge/gue_cx.jsp', 2, '1904', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('190407', '合并打印', 'YCWYPage/BackPage/print/print.jsp', 2, '1904', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('190408', '票据管理', 'YCWYPage/BackPage/print/pj.jsp', 2, '1904', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('1905', '人工自助', '0', 1, '19', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('190501', '人工收费', 'YCWYPage/BackPage/artificial/charge_manual.jsp', 2, '1905', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('190502', '自助缴费查询', 'YCWYPage/BackPage/artificial/charge_self.jsp', 2, '1905', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('1906', '交易管理', '0', 1, '19', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('190601', '对账查询', 'YCWYPage/BackPage/tstion/duizhang.jsp', 2, '1906', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('190602', '流水查询', 'YCWYPage/BackPage/tstion/liushui.jsp', 2, '1906', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('1907', '报表管理', '0', 1, '19', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('190701', '收费单查询', 'YCWYPage/BackPage/baobiao/charge_select.jsp', 2, '1907', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('190702', '批量导入查询', 'YCWYPage/BackPage/baobiao/gue_cx.jsp', 2, '1907', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('190703', '汇总报表', 'YCWYPage/BackPage/baobiao/baobiao.jsp', 2, '1907', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('190706', '年度预收统计', 'YCWYPage/BackPage/baobiao/ndys.jsp', 2, '1907', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('1908', '物业服务', '0', 1, '19', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('190801', '维修人员管理', 'YCWYPage/BackPage/ProperService/repairPeo.jsp', 2, '1908', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('190802', '报修信息管理', 'YCWYPage/BackPage/ProperService/repairInfo.jsp', 2, '1908', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('190803', '投诉建议查看', 'YCWYPage/BackPage/ProperService/tousu.jsp', 2, '1908', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('190804', '满意投票设置', 'YCWYPage/BackPage/ProperService/mytpsz.jsp', 2, '1908', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('190805', '问卷题目管理', 'YCWYPage/BackPage/ProperService/wjtm.jsp', 2, '1908', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('190806', '题目选项管理', 'YCWYPage/BackPage/ProperService/tmxx.jsp', 2, '1908', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('190807', '问卷记录查看', 'YCWYPage/BackPage/ProperService/wjjl.jsp', 2, '1908', 1);
INSERT INTO TB_CDE_MENU([menu_code], [menu_name], [url], [menu_level], [up_menu_code], [status]) VALUES ('190808', '公告管理', 'YCWYPage/BackPage/ProperService/gonggao.jsp', 2, '1908', 1);


UPDATE TB_CDE_MENU SET menu_code = '190205' where menu_code = '190204' and up_menu_code = '1902';
update TB_CDE_MENU SET menu_code = '190206' where menu_code = '190205' and up_menu_code = '1902';

insert into TB_CDE_MENU(menu_code,menu_name,url,menu_level,up_menu_code,status) values('190204','房屋类型管理','YCWYPage/BackPage/quarters/hostType.jsp','2','1902','1');


INSERT INTO TB_CDE_ROLE([role_code], [role_name], [description], [menu_code], [role_level], [status], [type], [tu_id], [hdlurl], [hurl], [qurl], [wurl], [wxurl]) VALUES (1343, '山西运城物业版本服务', '运城御溪物业', '19,1901,190101,190102,190103,190104,190105,1902,190201,190202,190203,190204,190205,1903,190301,190302,190303,190304,190305,190306,190307,1904,190401,190402,190403,190404,190405,190406,190407,190408,1905,190501,190502,1906,190601,190602,1907,190701,190702,190703,190706,1908,190801,190802,190803,190804,190805,190806,190807,190808', 0, 1, 2, 1, '', '', '', '', 'https://sx.eqiaotong.com/WYManage/WYPage/FrontPage/WyWXLogin.jsp');
