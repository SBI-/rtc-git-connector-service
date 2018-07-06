package org.jazzcommunity.GitConnectorService.service.github;

import com.google.gson.JsonObject;
import com.ibm.team.repository.service.TeamRawService;
import com.siemens.bt.jazz.services.base.rest.parameters.PathParameters;
import com.siemens.bt.jazz.services.base.rest.parameters.RestRequest;
import com.siemens.bt.jazz.services.base.rest.service.AbstractRestService;
import org.apache.commons.logging.Log;
import org.jazzcommunity.GitConnectorService.data.GithubConnection;
import org.jazzcommunity.GitConnectorService.net.ArtifactInformation;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IssuePreviewService extends AbstractRestService {

    public IssuePreviewService(Log log, HttpServletRequest request, HttpServletResponse response, RestRequest restRequest, TeamRawService parentService, PathParameters pathParameters) {
        super(log, request, response, restRequest, parentService, pathParameters);
    }

    @Override
    public void execute() throws Exception {
        ArtifactInformation information = new ArtifactInformation(
                request.getParameter("apiurl"),
                request.getParameter("weburl"));

        JsonObject issue = GithubConnection.getArtifact(information, parentService);

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/html/issue_preview.twig");
        JtwigModel model = JtwigModel.newModel()
                .with("title", issue.get("title").getAsString())
                .with("description", issue.get("body").getAsString())
                .with("state", issue.get("state").getAsString());

        response.setContentType("text/html");
        template.render(model, response.getOutputStream());
    }
}
