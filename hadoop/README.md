# Hadoop
## Table of contents
* [Introduction](#Introduction)
* [The Hadoop Ecosystem](#The-Hadoop-Ecosystem)
* [Cluster Architecture](#Cluster-Architecture)
* [Hive Project](#Hive-Project)
* [Improvements](#Improvements)

## Introduction
The Data Analytics team recently switched to Apache Hadoop to process big data;
as the Data Engineer, I was responsible for:
  - Evaluating core Hadoop components (HDFS, YARN, and MapReduce)
  - Provisioning a Hadoop cluster using Google Cloud Platform (GCP)
  - Performing data optimizations/analytics using Apache Hive and Zeppelin Notebook

I was tasked with examining the 2016 World Development Indicators dataset (WDI is
the World Bank’s premier compilation of international statistics on global
development) and doing data processing/analysis using the Hadoop ecosystem;
specifically, I deployed a 3-node Hadoop cluster on GCP and performed data optimizations
for data analytics operations by leveraging HDFS, YARN, Hive, and Zeppelin functionality.

## The Hadoop Ecosystem
 Apache Hadoop is a collection of open-source frameworks that are used to efficiently
 store and process large datasets ranging in size from gigabytes to petabytes of data.
 Instead of using one large computer to store and process the data, Hadoop allows
 clustering multiple computers to analyze massive datasets in parallel more quickly.  

 Hadoop consists of four main components:
 - Hadoop Distributed File System (HDFS) – A distributed file system that runs on
   standard or low-end hardware
 - Yet Another Resource Negotiator (YARN) – Manages and monitors cluster nodes and
   resource usage
 - MapReduce – A framework that helps programs do the parallel computation on data 

 MapReduce is the original programming paradigm and execution engine that was introduced
 with Hadoop; however, more efficient execution engines derived from MapReduce have
 emerged (Tez, Spark, Giraph, Impala, etc.) and can be run on top of YARN instead of
 MapReduce.  

 ![Hadoop](assets/hadoop.png)  

 Hive is a data warehouse framework that runs on top of Hadoop HDFS and YARN and
 offers an SQL-like query language (HiveQL/HQL) that can process large datasets
 stored in HDFS (or other compatible File Systems, like Amazon S3);
 Hive converts HQL queries to
 MapReduce (or Tez, Spark, etc.) jobs, which process data residing on HDFS; therefore,
 Hive does not store the actual data; instead, it uses a Metastore to manage metadata
 in an RDBMS of your choice. Hive metadata is table schemas (table name,
 column names, HDFS location, SerDe information, etc.) that correspond to the 
 underlying data files in HDFS, and is used to load data files into database objects.
 We can connect to the Hive service through various clients; the Zeppelin web-based
 notebook was used in this case, which connects to the Hive service through JDBC
 and allows data analysis code and documentation using multiple interpreters/languages
 (Markdown, HiveQL, SparkSQL, Bash, etc.).  

 ![Hive](assets/hive.png)  

## Cluster Architecture:
Deployed a 3-node Hadoop cluster using GCP Dataproc with 1 master node and 2 worker
nodes; all 3 nodes have the same hardware specifications: 2 vCPUs, 13GB RAM and 100GB
HDD; Zeppelin Notebook was added as an additional component to the project when
creating the cluster. Dataproc uses image versions to bundle operating-system, big data
components, and GCP connectors into one package that is deployed on a cluster; the 
cluster image used for this project is 1.3.74-debian10, which includes Hadoop 2.9.2 and
Hive 2.3.7. The dataset was imported to GCP Storage of the same project to provide 
access to it from the cluster. Both HDFS Name Node and YARN Resource Manager reside on
the master node, while the respective Data nodes, Node Managers, and Application
Masters reside on the worker nodes; the Hive and Zeppelin services also operate from
the master node.  

![Cluster](assets/cluster.png)  

## Hive Project
The 2016 World Development Indicators dataset was duplicated onto HDFS from GCP Storage;
Hive tables were created from both sources (GCP and HDFS) and were used to examine
the data, optimize it (SerDes, Partitioning, Columnar-Formats), perform data analytics operations,
and document findings through the Markdown,
HiveQL, and Bash interpreters available on the Zeppelin Notebook. Hive's default
execution engine used to be MapReduce but was replaced recently by Apache Tez;
Tez improves the MapReduce paradigm by dramatically improving its speed while
maintaining its ability to scale to petabytes of data. Therefore, HiveQL queries
produce Tez tasks by default, although switching to MapReduce and Spark is an option.
A snapshot of the notebook can be found below:  

![Notebook](assets/notebook.png)  

## Improvements
- Compare performance and methodology between MapReduce, Tez, and Spark more in-depth
- More experimentation with bucketing and map-side joins in Hive to improve performance 
- Use a bigger Hadoop cluster to improve performance and reliability
- Explore alternatives to Hive, such as Impala, PrestoDB, Spark, Shark, among others
