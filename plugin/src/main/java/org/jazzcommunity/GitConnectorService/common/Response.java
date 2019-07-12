package org.jazzcommunity.GitConnectorService.common;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.apache.http.entity.ContentType;

public final class Response {
  private Response() {}

  public static <T> void marshallXml(HttpServletResponse response, T answer)
      throws JAXBException, IOException {
    response.setContentType(ContentType.APPLICATION_XML.toString());
    response.setCharacterEncoding("UTF-8");
    Marshaller marshaller = JAXBContext.newInstance(answer.getClass()).createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    marshaller.marshal(answer, response.getWriter());
  }
}