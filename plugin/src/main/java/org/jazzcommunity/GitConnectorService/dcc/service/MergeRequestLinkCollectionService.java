package org.jazzcommunity.GitConnectorService.dcc.service;

import com.ibm.team.repository.service.TeamRawService;
import com.siemens.bt.jazz.services.base.rest.parameters.PathParameters;
import com.siemens.bt.jazz.services.base.rest.parameters.RestRequest;
import com.siemens.bt.jazz.services.base.rest.service.AbstractRestService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.jazzcommunity.GitConnectorService.common.GitLink;
import org.jazzcommunity.GitConnectorService.common.Parameter;
import org.jazzcommunity.GitConnectorService.common.Response;
import org.jazzcommunity.GitConnectorService.common.WorkItemLinkCollector;
import org.jazzcommunity.GitConnectorService.dcc.controller.LinkCollectionController;
import org.jazzcommunity.GitConnectorService.dcc.xml.MergeRequestLinks;
import org.jazzcommunity.GitConnectorService.dcc.xml.PaginatedCollection;

public class MergeRequestLinkCollectionService extends AbstractRestService {

  public MergeRequestLinkCollectionService(
      Log log,
      HttpServletRequest request,
      HttpServletResponse response,
      RestRequest restRequest,
      TeamRawService parentService,
      PathParameters pathParameters) {
    super(log, request, response, restRequest, parentService, pathParameters);
  }

  @Override
  public void execute() throws Exception {
    String id = Parameter.handleId(request);
    WorkItemLinkCollector collector = new WorkItemLinkCollector(GitLink.GIT_REQUEST, parentService);
    LinkCollectionController controller =
        new LinkCollectionController(collector, parentService.getRequestRepositoryURL());
    PaginatedCollection answer = controller.fillPayload(request, id, new MergeRequestLinks());

    Response.marshallXml(response, answer);
  }
}
