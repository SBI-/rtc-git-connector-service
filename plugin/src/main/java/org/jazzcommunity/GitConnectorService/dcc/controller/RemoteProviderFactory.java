package org.jazzcommunity.GitConnectorService.dcc.controller;

import ch.sbi.minigit.gitlab.GitlabApi;
import ch.sbi.minigit.gitlab.GitlabWebFactory;
import com.ibm.team.git.common.model.IGitRepositoryDescriptor;
import java.net.URL;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.jazzcommunity.GitConnectorService.dcc.data.PageProvider;
import org.jazzcommunity.GitConnectorService.dcc.net.UrlParser;

public class RemoteProviderFactory<T> {

  private final int timeout;
  private final String type;
  private final Class<T[]> collectionType;
  private final String modified;
  private final IGitRepositoryDescriptor[] repositories;
  private final Log log;

  public RemoteProviderFactory(
      String type,
      Class<T[]> collectionType,
      int timeout,
      String modified,
      IGitRepositoryDescriptor[] repositories,
      Log log) {
    this.timeout = timeout;
    this.type = type;
    this.collectionType = collectionType;
    this.modified = modified;
    this.repositories = repositories;
    this.log = log;
  }

  public PageProvider<T> getProvider(String token) {
    PageProvider<T> provider = new PageProvider<>(type, collectionType);

    for (IGitRepositoryDescriptor repository : repositories) {
      try {
        URL url = new URL(repository.getUrl());
        String baseUrl = UrlParser.getBaseUrl(url);
        GitlabWebFactory factory =
            new GitlabWebFactory(baseUrl)
                .setTimeout(timeout)
                .setToken(token)
                .addQueryParameter("updated_after", modified);

        // only set token if it could be valid, because otherwise the request will always return an empty payload
        if (StringUtils.isNotEmpty(token)) {
          factory.setToken(token);
        }

        GitlabApi api = factory.build();
        provider.addRepository(api, url);
      } catch (Exception e) {
        String message =
            String.format(
                "Repository at '%s' could not be reached or is not a gitlab repository: '%s'",
                repository.getUrl(), e.getMessage());

        log.info(message);
      }
    }

    return provider;
  }
}
