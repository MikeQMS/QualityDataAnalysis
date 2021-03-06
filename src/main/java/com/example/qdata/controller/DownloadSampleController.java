/*
    <Q-Data Analytics tool with xlsx import and MySQL DB>
    Copyright (C) 2022-  MikeQMS

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.example.qdata.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
@RestController
@RequestMapping("/download")
public class DownloadSampleController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(DownloadSampleController.class);

    @Autowired
    private ResourceLoader resourceLoader;

    @RequestMapping("/file/{fileName:.+}")
    public void downloadSampleCSV(HttpServletRequest request,
                                  HttpServletResponse response,
                                  @PathVariable("fileName") String fileName) throws IOException {

        LOGGER.info("Inside the download controller," +
                " resource fileName =" + fileName);
        Resource resource = resourceLoader
                .getResource("classpath:" + fileName);
        if (resource.exists()) {
            LOGGER.info("Resource exists!");
            response.setContentType("text/csv");
            response.setHeader("Content-Disposition",
                    String.format("attachment; filename=" +
                            resource.getFilename()));
            response.setContentLength((int) resource.contentLength());
            InputStream inputStream = resource.getInputStream();
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        }
    }
}
