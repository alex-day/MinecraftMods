package net.minecraft.src.NodePack.Shared;

import java.io.File;
import java.io.FilenameFilter;

public class Node_OptionsFilter
    implements FilenameFilter
{

    public Node_OptionsFilter()
    {
    }

    public boolean accept(File file, String s)
    {
        return s.matches("NodeOptions_[\\w]*.txt");
    }
}
