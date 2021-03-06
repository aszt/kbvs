/**
 *
 */
package kbvs.util;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

import java.io.*;
import java.util.Enumeration;

/**
 * @author yangfei
 * @since 2013-1-27
 */
public class Zipper {

    static final int BUFFER = 1024;

    public static void zip(String destDirPath, String inputPath)
            throws Exception {
        File inputFile = new File(inputPath);
        // 创建压缩文件
        File destDir = new File(destDirPath);
        if (!destDir.exists()) {
            destDir.mkdir();
        }
        File destFile = new File(destDir + File.separator + inputFile.getName()
                + ".zip");
        // 递归压缩方法
        ZipOutputStream out = new ZipOutputStream(
                new FileOutputStream(destFile));
        // 设置压缩编码.设置为GBK后在windows下就不会乱码了，如果要放到Linux或者Unix下就不要设置了
        out.setEncoding("GBK");
        zip(out, inputFile, "");
        System.out.println("zip done");
        out.close();
    }

    private static void zip(ZipOutputStream out, File f, String base)
            throws Exception {
        System.out.println("Zipping   " + f.getName()); // 记录日志，开始压缩
        if (f.isDirectory()) { // 如果是文件夹，则获取下面的所有文件
            File[] fl = f.listFiles();
            if (base.length() > 0) {

                out.putNextEntry(new ZipEntry(base + "/"));// 此处要将文件写到文件夹中只用在文件名前加"/"再加文件夹名
            }

            base = base.length() == 0 ? "" : base + "/";
            for (int i = 0; i < fl.length; i++) {
                zip(out, fl[i], base + fl[i].getName());
            }
        } else { // 如果是文件，则压缩
            out.putNextEntry(new ZipEntry(base)); // 生成下一个压缩节点
            FileInputStream in = new FileInputStream(f); // 读取文件内容
            int len;
            byte[] buf = new byte[BUFFER];
            while ((len = in.read(buf, 0, BUFFER)) != -1) {
                out.write(buf, 0, len); // 写入到压缩包
            }
            in.close();
            out.closeEntry();
        }
    }

    /**
     * 解压缩zip文件
     *
     * @param fileName
     *            要解压的文件名 包含路径 如："c:\\test.zip"
     * @param destFilePath
     *            解压后存放文件的路径 如："c:\\temp"
     * @throws Exception
     */
    public static void unZip(String fileName, String destFilePath)
            throws Exception {
        ZipFile zipFile = new ZipFile(fileName, "GBK"); // 以“GBK”编码创建zip文件，用来处理winRAR压缩的文件。
        Enumeration emu = zipFile.getEntries();
        while (emu.hasMoreElements()) {
            ZipEntry entry = (ZipEntry) emu.nextElement();
            if (entry.isDirectory()) {
                File dir = new File(destFilePath + entry.getName());
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                continue;
            }
            BufferedInputStream bis = new BufferedInputStream(zipFile
                    .getInputStream(entry));

            File file = new File(destFilePath + entry.getName());
            File parent = file.getParentFile();
            if (parent != null && (!parent.exists())) {
                parent.mkdirs();
            }
            FileOutputStream fos = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(fos, BUFFER);
            byte[] buf = new byte[BUFFER];
            int len = 0;
            while ((len = bis.read(buf, 0, BUFFER)) != -1) {
                fos.write(buf, 0, len);
            }
            bos.flush();
            bos.close();
            bis.close();
            System.out.println("解压文件：" + file.getName());
        }
        zipFile.close();
    }
}
