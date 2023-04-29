package com.a2.P4DirectoryFinder;

import java.io.File;
import java.io.IOException;

public class NewDirectoryDisplay {

  /*
   * Lets just say that now I am much more comfortable with recursion methods,
   * while before I was terrified/thought it was a headache to set up and the
   * difference is night and day
   */

  LeftRightSiblingTreeNode<String> root;

  public void walkThrough() throws IOException {
    root.walkThrough();
  }

  public NewDirectoryDisplay(String rootPath) throws IOException {
    File directory = new File(rootPath);
    root = new LeftRightSiblingTreeNode<String>(directory.getName());
    root.pathToObject = directory.getPath();
    if (directory.isDirectory()) {
      buildTree(root, directory, "-");
    }
  }

  public void buildTree(LeftRightSiblingTreeNode<String> node, File directory, String depth) throws IOException {

    for (File filesIn : directory.listFiles()) {
      node = node.appendChildToThisTree(depth + filesIn.getName(), directory.getPath());
      if (filesIn.isDirectory()) {
        buildTree(node, filesIn, (depth + "-"));
      }
    }
  }

  public void searchObjectInDirectory(String file) {
    root.walkAndSearchThrough(file);
  }
}
