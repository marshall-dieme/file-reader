package com.opentech.filereader.application.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opentech.filereader.application.model.MyModel;
import com.opentech.filereader.application.repository.MyRepository;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class MyService {

	private final MyRepository repo;

	public List<MyModel> readAndSave(MultipartFile file) {
		String ext = getFileExtension(file);
		List<MyModel> data = new ArrayList<>();

		if (ext != null) {
			switch (ext) {
				case "xlsx", "xls":
					data = readAndSaveXLS(file);
					break;
				case "csv":
					data = readAndSaveCSV(file);
					break;
				case "json":
					data = readAndSaveJSON(file);
					break;
				default:
					throw new AssertionError();
			}
		}

		return data;
	}

	private List<MyModel> readAndSaveXLS(MultipartFile file) {
		try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
			Sheet sheet = workbook.getSheetAt(0);

			List<MyModel> models = new ArrayList<>();

			for (Row row : sheet) {
				if(row.getRowNum() == 0)
					continue;
				//if ID exist in Numeric format add the getNumericCellValue() right after the getCell method
				MyModel model = MyModel.builder()
					.id(row.getCell(0).getStringCellValue())
					.attr1(row.getCell(1).getStringCellValue())
					.attr2(row.getCell(2).getStringCellValue())
					.attr3(row.getCell(3).getStringCellValue())
					.attr4(row.getCell(4).getStringCellValue())
					.build();

				models.add(model);
			}

			return repo.saveAll(models);
		}
		catch (Exception err) {
			log.error("An error occurs : ${}", err.getMessage());
		}
		return new ArrayList<>();
	}

	private List<MyModel> readAndSaveCSV(MultipartFile file) {

		try(CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
			List<MyModel> models = new ArrayList<>();
			String[] nextRecord;

			while((nextRecord = reader.readNext()) != null) {
				MyModel model = MyModel.builder()
					.id(nextRecord[0])
					.attr1(nextRecord[1])
					.attr2(nextRecord[2])
					.attr3(nextRecord[3])
					.attr4(nextRecord[4])
					.build();

				models.add(model);
			}

			return repo.saveAll(models);

		} catch(Exception err) {
			log.error("An error occurs : ${}", err.getMessage());
		}

		return new ArrayList<>();
	}

	private List<MyModel> readAndSaveJSON(MultipartFile file) {
		try {
			ObjectMapper mapper = new ObjectMapper();

			List<MyModel> models = mapper.readValue(file.getInputStream(), new TypeReference<List<MyModel>>() {});

			return repo.saveAll(models);
		}
		catch (Exception err) {
			log.error("An error occurs : ${}", err.getMessage());
		}
		return new ArrayList<>();
	}


	private String getFileExtension(MultipartFile file) {
		String originalName = file.getOriginalFilename();

		if (originalName != null) {
			int dotIndex = originalName.lastIndexOf('.');
			if (dotIndex > 0) {
				return originalName.substring(dotIndex + 1).toLowerCase();
			}
		}
		return null;
	}
}
