package io.renren.modules.sys.controller;



		import com.aliyun.oss.OSSClient;
		import io.renren.common.config.OSSConfig;
		import io.renren.common.enums.ResultEnum;
		import io.renren.common.utils.OSSUtil;
		import io.renren.common.utils.R;
		import org.springframework.web.bind.annotation.*;
		import org.springframework.web.multipart.MultipartFile;

		import java.util.HashMap;
		import java.util.Map;

/**
 * Created by yhy
 * 2017-10-22 14:27
 */
@RestController
@RequestMapping("/common")
public class UploadFileController {

	/**
	 * 文件上传具体方法
	 * @param file
	 * @return
	 */
	@PostMapping("/upload")
	public R UploadImage(@RequestParam("file") MultipartFile file){

		System.out.println("文件上传开始");

		if(file.isEmpty()){
		    return R.error(ResultEnum.FILE_ISNELL.getCode(),ResultEnum.FILE_ISNELL.getMessage());
		}

		if(!fileType(file.getOriginalFilename())){
			return R.error(ResultEnum.IMAGE_TYPE_ERROR.getCode(),ResultEnum.IMAGE_TYPE_ERROR.getMessage());
		}

		OSSClient ossClient= OSSUtil.getOSSClient();
		String path = OSSUtil.uploadObject2OSS(ossClient,file, OSSConfig.folder);
		System.out.println(path);
		System.out.println("文件上传结束");
		return R.ok(path);
	}

	/**
	 * 文件上传具体方法
	 * @param file
	 * @return
	 */
	@PostMapping("/uploadImg")
	public R uploadImg(@RequestParam("file") MultipartFile file){

		System.out.println("文件上传开始");

		if(file.isEmpty()){
			return R.error(ResultEnum.FILE_ISNELL.getCode(),ResultEnum.FILE_ISNELL.getMessage());
		}

		if(!fileType(file.getOriginalFilename())){
			return R.error(ResultEnum.IMAGE_TYPE_ERROR.getCode(),ResultEnum.IMAGE_TYPE_ERROR.getMessage());
		}

		OSSClient ossClient= OSSUtil.getOSSClient();
		String path = OSSUtil.uploadObject2OSS(ossClient,file, OSSConfig.folder);
		Map<String,Object> map = new HashMap<>();
		map.put("error",0);
		map.put("url",path);
		System.out.println(path);
		System.out.println("文件上传结束");
		return R.ok(map);
	}

	/**
	 * 文件上传具体方法
	 * @param file
	 * @return
	 */
	@PostMapping("/UploadFile")
	public R UploadFile(@RequestParam("file") MultipartFile file){

		System.out.println("文件上传开始");

		OSSClient ossClient= OSSUtil.getOSSClient();
		String path = OSSUtil.uploadObject2OSS(ossClient,file, OSSConfig.folder);
		System.out.println(path);
		System.out.println("文件上传结束");
		return R.ok(path);
	}

	@PostMapping("/uploadEdit")
	public R uploadEdit(@RequestParam("file") MultipartFile file){
		System.out.println("文件上传开始");
		OSSClient ossClient= OSSUtil.getOSSClient();
		String path = OSSUtil.uploadObject2OSS(ossClient,file, OSSConfig.folder);
		Map<String,String> map = new HashMap<>();
		map.put("src",path);
		System.out.println("文件上传结束");
		return R.ok().put("data",map);
	}

	public boolean fileType(String fileName) {

		// 获取文件后缀名并转化为写，用于后续比较
		String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
		// 创建图片类型数组
		String img[] = { "jpg", "jpeg", "png"};
		for (int i = 0; i < img.length; i++) {
			if (img[i].equals(fileType)) {
				return true;
			}
		}
		return false;
	}

}
