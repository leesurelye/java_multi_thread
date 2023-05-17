# ==== CREATE CATALOG ===
create catalog if not exists hive2 as 'hive2' clusterId 'cc405925-7055-429b-a59f-c7c9da1cc8e5' with ('username': 'root','port': '9083','host': '10.5.30.3','password': 'Aloudata@12','synccataloginterval': '5400000');
create catalog if not exists mysql as 'mysql1' clusterId 'cc405925-7055-429b-a59f-c7c9da1cc8e5' with with ('username': 'root','port': '9083','host': '10.5.30.3','password': 'Aloudata@12');
create external catalog if not exists HIVE3 as 'hive4' clusterId 'd69290a4-f9b0-4d41-a230-441bbc85bea7'  with ('username':'','password':'',   'catalogType':'HIVE',   'resources':'/data/hadoop-2.8.2/etc/hadoop/core-site.xml,/data/hadoop-2.8.2/etc/hadoop/hdfs-site.xml', 'connect':'thrift://10.5.30.3:9083')
create external catalog if not exists hive3 as 'hive1' clusterId 'cc405925-7055-429b-a59f-c7c9da1cc8e5' with ('username': 'root','port': '9083','host': '10.5.30.3','password': 'Aloudata@12','synccataloginterval': '5400000');

# == ALTER CATALOG  ===
alter catalog  'hive1' clusterId 'cc405925-7055-429b-a59f-c7c9da1cc8e5' with ('username': 'root','port': '9083','host': '10.5.30.3','password': 'Aloudata@12','synccataloginterval': '5400000', 'connect':'jdbc:mysql://10.53.20.26:3306');

# ===  SHOW CATALOG ===
show catalogs from 'cc405925-7055-429b-a59f-c7c9da1cc8e5';
show create catalog 'cc405925-7055-429b-a59f-c7c9da1cc8e5'.'hive1';

# === DROP Catalog ====
drop catalog 'hive1' from 'cc405925-7055-429b-a59f-c7c9da1cc8e5';
drop catalog 'hive2' from 'cc405925-7055-429b-a59f-c7c9da1cc8e5';

