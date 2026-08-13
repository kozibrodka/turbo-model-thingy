package net.kozibrodka.tmt;

import net.glasslauncher.mods.gcapi3.api.ConfigRoot;
import net.kozibrodka.tmt.glasscfg.TmtConfig;

public class mod_tmt {

    @ConfigRoot(value = "TMT", visibleName = "TMT Config")
    public static final TmtConfig tmtGlass = new TmtConfig();
}
