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

        @ConfDefault.DefaultStrings({"这个箱子是你的了!"})
        List<String> setMaster();

        @ConfDefault.DefaultStrings({"你没有权限设置这个箱子的主人!"})
        List<String> notHasPermissionSetMaster();

        @ConfDefault.DefaultStrings({"master is [%s]"})
        List<String> info();
    }
}
