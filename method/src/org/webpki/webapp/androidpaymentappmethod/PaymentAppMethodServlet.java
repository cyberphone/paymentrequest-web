/*
 *  Copyright 2015-2018 WebPKI.org (http://webpki.org).
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package org.webpki.webapp.androidpaymentappmethod;

import java.io.IOException;

import java.util.logging.Logger;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.webpki.json.JSONOutputFormats;
import org.webpki.json.JSONParser;

public class PaymentAppMethodServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    static Logger logger = Logger.getLogger(PaymentAppMethodServlet.class.getCanonicalName());

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        logger.info("GET");
        response.setContentType("text/plain");
        response.getOutputStream().write(
            JSONParser.parse(
                PaymentAppMethodService.paymentManifest).serializeToBytes(JSONOutputFormats.PRETTY_PRINT));
    }

    @Override
    public void doHead(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        logger.info("HEAD");
        response.setHeader("ETag", PaymentAppMethodService.eTag);
        response.setHeader("Link", "<payment-manifest.json>; rel=\"payment-method-manifest\"");
    }
}
