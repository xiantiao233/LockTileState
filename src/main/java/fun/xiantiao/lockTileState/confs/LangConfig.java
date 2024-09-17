package fun.xiantiao.lockTileState.confs;

import space.arim.dazzleconf.annote.*;

import java.util.List;

@SuppressWarnings("unused")
@ConfHeader("# 配置文件 LockTileState Config ver 1.0.0 by xiantiao")
public interface LangConfig {

    Locker locker();

    @SubSection
    interface Locker {
        @ConfDefault.DefaultStrings({"你没有权限!"})
        List<String> notHasPermission();
    }
}
