# @Transactional(rollbackFor = Exception.class) 的作用

在项目中，@Transactional(rollbackFor=Exception.class)，如果类加了这个注解，那么这个类里面的方法抛出异常，就会回滚，数据库里面的数据也会回滚。

**这种设置是因为Spring的默认回滚RuntimeException，
如果想要回滚Exception时，要设置@Transactional(rollbackFor = Exception.class)，而且Exception还要抛出。**