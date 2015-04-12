# mapreduce_sample

```
# garbagetown at garbagetown.local in ~/dev/repos [17:02:10]
$ g clone git@github.com:tamtam180/apache_log_gen.git
Cloning into 'apache_log_gen'...
remote: Counting objects: 86, done.
remote: Total 86 (delta 0), reused 0 (delta 0), pack-reused 86
Receiving objects: 100% (86/86), 23.69 KiB | 0 bytes/s, done.
Resolving deltas: 100% (29/29), done.
Checking connectivity... done.

# garbagetown at garbagetown.local in ~/dev/repos [17:02:25]
$ apache_log_gen

# garbagetown at garbagetown.local in ~/dev/repos/apache_log_gen on git:master o [17:02:35]
$ gem install apache-loggen
Fetching: apache-loggen-0.0.4.gem (100%)
Successfully installed apache-loggen-0.0.4
1 gem installed
Installing ri documentation for apache-loggen-0.0.4...
Installing RDoc documentation for apache-loggen-0.0.4...

# garbagetown at garbagetown.local in ~/dev/repos/apache_log_gen on git:master o [17:02:51]
$ ~/dev/repos/mapreduce_sample

# garbagetown at garbagetown.local in ~/dev/repos/mapreduce_sample/src/scripts on git:master x [17:04:11]
$ ruby src/scripts/my_generator.rb --limit=100 access_log

# garbagetown at garbagetown.local in ~/dev/repos/mapreduce_sample on git:master x [16:18:11]
$ mv access_log ~/dev/vagrants/cdh5

# garbagetown at garbagetown.local in ~/dev/repos/mapreduce_sample on git:master x [16:09:21]
$ mvn clean package
[INFO] Scanning for projects...
(snip)
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 5.721 s
[INFO] Finished at: 2015-04-12T16:11:24+09:00
[INFO] Final Memory: 20M/106M
[INFO] ------------------------------------------------------------------------

# garbagetown at garbagetown.local in ~/dev/repos/mapreduce_sample on git:master x [16:11:25]
$ mv target/mapreduce_sample-1.0-SNAPSHOT.jar ~/dev/vagrants/cdh5

# garbagetown at garbagetown.local in ~/dev/repos/mapreduce_sample on git:master x [16:18:20]
$ ~/dev/vagrants/cdh5

# garbagetown at garbagetown.local in ~/dev/vagrants/cdh5 [18:25:13]
$ vagrant ssh
Last login: Sun Apr 12 18:25:19 2015 from 10.0.2.2
Welcome to your Vagrant-built virtual machine.

[vagrant@localhost ~]$ hadoop fs -mkdir input
[vagrant@localhost ~]$ hadoop fs -put /vagrant/access_log input
[vagrant@localhost ~]$ hadoop jar /vagrant/mapreduce_sample-1.0-SNAPSHOT.jar garbagetown.MyDriver input output
(snip)

[vagrant@localhost ~]$ hadoop fs -ls output
Found 3 items
-rw-r--r--   1 vagrant supergroup          0 2015-04-12 18:59 output/_SUCCESS
-rw-r--r--   1 vagrant supergroup        488 2015-04-12 18:59 output/part-r-00000
-rw-r--r--   1 vagrant supergroup        501 2015-04-12 18:59 output/part-r-00001

[vagrant@localhost ~]$ hadoop fs -cat output/part-r-00000
/category/electronics	15	7
/category/games	8	5
/category/garden	1	1
(snip)
```