package kbvs.converter;

import kbvs.util.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SWFConverter implements ISWFConverter{
    //注意destPath最后必须带文件分隔符
    //这个用来转换的swfTool工具，一但待转换的文件有不可识别的字符在里面时，它就会堵塞在那里。这个情况要处理掉
    public String  convert2SWF(String sourcePath,String destPath) throws Exception {
        // 源文件不存在则返回
        File source = new File(sourcePath);
        if (!source.exists()) {
            System.out.println("pdf转换swf失败，源文件不存在!");
            return null;
        }
        //因为下面进行系统调用，这样就会把系统执行的操作新开启一个线程（在此linux也叫进程），所以它和主扫描程序是独立运行，所以下次还会扫描这个转换中的文件，所以这里要将它设置为不可读，
        source.setReadable(false);
//        String outputFile =destPath.replace(".swf", "_%.swf");//每页生成一个swf文件
		String outputFile =destPath;//只生成一个swf文件
        System.out.println("开始调用swftools转换pdf文件:" + outputFile);
        List<String>  command = new	ArrayList<String>();
        command.add("C:\\Program Files (x86)\\SWFTools\\pdf2swf.exe");//从配置文件里读取
        command.add(sourcePath);
        command.add("-o");
        command.add(outputFile);
        command.add("-T");
		command.add("9");
		command.add("-f");

//		command.add("-s");
//		command.add("poly2bitmap");//加入poly2bitmap的目的是为了防止出现大文件或图形过多的文件转换时的出错，没有生成swf文件的异常
//        command.add("-s");
//        command.add("drawonlyshapes");

        command.add("-s");
//        command.add("flashversion=9");
        //windows平台下
		command.add("languagedir=C:/xpdf/chinese-simplified/");
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(command);
        Process process = processBuilder.start();

//		dealWith(process);//改用下面的方式来处理：
        InputStreamWathThread inputWathThread = new InputStreamWathThread(process);
        inputWathThread.start();
        ErrorInputStreamWathThread errorInputWathThread = new ErrorInputStreamWathThread(process);
        System.out.println("开始转换swf");
        errorInputWathThread.start();
        try {
            process.waitFor();//等待子进程的结束，子进程就是系统调用文件转换这个新进程
            System.out.println("转换swf结束");
            inputWathThread.setOver(true);//转换完，停止流的处理
            errorInputWathThread.setOver(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.err.println(e);
            return null;
        }
        File file = new File(outputFile);

        if (!file.exists()) {
            System.out.println("pdf转换swf失败，源文件不存在!");
            return null;
        }
        return FileUtils.getRelPath(outputFile);
    }
    @Override
    public String  convert2SWF(String inputFile) throws Exception {
        String swfFile = FileUtils.getFilePrefix(inputFile)+".swf";
        return convert2SWF(inputFile,swfFile);
    }
}

