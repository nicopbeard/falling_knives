"""Generated client library for clouderrorreporting version v1beta1."""
# NOTE: This file is autogenerated and should not be edited by hand.
from apitools.base.py import base_api
from googlecloudsdk.third_party.apis.clouderrorreporting.v1beta1 import clouderrorreporting_v1beta1_messages as messages


class ClouderrorreportingV1beta1(base_api.BaseApiClient):
  """Generated client library for service clouderrorreporting version v1beta1."""

  MESSAGES_MODULE = messages
  BASE_URL = u'https://clouderrorreporting.googleapis.com/'
  MTLS_BASE_URL = u'https://clouderrorreporting.mtls.googleapis.com/'

  _PACKAGE = u'clouderrorreporting'
  _SCOPES = [u'https://www.googleapis.com/auth/cloud-platform']
  _VERSION = u'v1beta1'
  _CLIENT_ID = '1042881264118.apps.googleusercontent.com'
  _CLIENT_SECRET = 'x_Tw5K8nnjoRAqULM9PFAC2b'
  _USER_AGENT = u'google-cloud-sdk'
  _CLIENT_CLASS_NAME = u'ClouderrorreportingV1beta1'
  _URL_VERSION = u'v1beta1'
  _API_KEY = None

  def __init__(self, url='', credentials=None,
               get_credentials=True, http=None, model=None,
               log_request=False, log_response=False,
               credentials_args=None, default_global_params=None,
               additional_http_headers=None, response_encoding=None):
    """Create a new clouderrorreporting handle."""
    url = url or self.BASE_URL
    super(ClouderrorreportingV1beta1, self).__init__(
        url, credentials=credentials,
        get_credentials=get_credentials, http=http, model=model,
        log_request=log_request, log_response=log_response,
        credentials_args=credentials_args,
        default_global_params=default_global_params,
        additional_http_headers=additional_http_headers,
        response_encoding=response_encoding)
    self.projects_events = self.ProjectsEventsService(self)
    self.projects_groupStats = self.ProjectsGroupStatsService(self)
    self.projects_groups = self.ProjectsGroupsService(self)
    self.projects = self.ProjectsService(self)

  class ProjectsEventsService(base_api.BaseApiService):
    """Service class for the projects_events resource."""

    _NAME = u'projects_events'

    def __init__(self, client):
      super(ClouderrorreportingV1beta1.ProjectsEventsService, self).__init__(client)
      self._upload_configs = {
          }

    def List(self, request, global_params=None):
      r"""Lists the specified events.

      Args:
        request: (ClouderrorreportingProjectsEventsListRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (ListEventsResponse) The response message.
      """
      config = self.GetMethodConfig('List')
      return self._RunMethod(
          config, request, global_params=global_params)

    List.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1beta1/projects/{projectsId}/events',
        http_method=u'GET',
        method_id=u'clouderrorreporting.projects.events.list',
        ordered_params=[u'projectName'],
        path_params=[u'projectName'],
        query_params=[u'groupId', u'pageSize', u'pageToken', u'serviceFilter_resourceType', u'serviceFilter_service', u'serviceFilter_version', u'timeRange_period'],
        relative_path=u'v1beta1/{+projectName}/events',
        request_field='',
        request_type_name=u'ClouderrorreportingProjectsEventsListRequest',
        response_type_name=u'ListEventsResponse',
        supports_download=False,
    )

    def Report(self, request, global_params=None):
      r"""Report an individual error event.

This endpoint accepts **either** an OAuth token,
**or** an [API key](https://support.google.com/cloud/answer/6158862)
for authentication. To use an API key, append it to the URL as the value of
a `key` parameter. For example:

`POST
https://clouderrorreporting.googleapis.com/v1beta1/{projectName}/events:report?key=123ABC456`

      Args:
        request: (ClouderrorreportingProjectsEventsReportRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (ReportErrorEventResponse) The response message.
      """
      config = self.GetMethodConfig('Report')
      return self._RunMethod(
          config, request, global_params=global_params)

    Report.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1beta1/projects/{projectsId}/events:report',
        http_method=u'POST',
        method_id=u'clouderrorreporting.projects.events.report',
        ordered_params=[u'projectName'],
        path_params=[u'projectName'],
        query_params=[],
        relative_path=u'v1beta1/{+projectName}/events:report',
        request_field=u'reportedErrorEvent',
        request_type_name=u'ClouderrorreportingProjectsEventsReportRequest',
        response_type_name=u'ReportErrorEventResponse',
        supports_download=False,
    )

  class ProjectsGroupStatsService(base_api.BaseApiService):
    """Service class for the projects_groupStats resource."""

    _NAME = u'projects_groupStats'

    def __init__(self, client):
      super(ClouderrorreportingV1beta1.ProjectsGroupStatsService, self).__init__(client)
      self._upload_configs = {
          }

    def List(self, request, global_params=None):
      r"""Lists the specified groups.

      Args:
        request: (ClouderrorreportingProjectsGroupStatsListRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (ListGroupStatsResponse) The response message.
      """
      config = self.GetMethodConfig('List')
      return self._RunMethod(
          config, request, global_params=global_params)

    List.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1beta1/projects/{projectsId}/groupStats',
        http_method=u'GET',
        method_id=u'clouderrorreporting.projects.groupStats.list',
        ordered_params=[u'projectName'],
        path_params=[u'projectName'],
        query_params=[u'alignment', u'alignmentTime', u'groupId', u'order', u'pageSize', u'pageToken', u'serviceFilter_resourceType', u'serviceFilter_service', u'serviceFilter_version', u'timeRange_period', u'timedCountDuration'],
        relative_path=u'v1beta1/{+projectName}/groupStats',
        request_field='',
        request_type_name=u'ClouderrorreportingProjectsGroupStatsListRequest',
        response_type_name=u'ListGroupStatsResponse',
        supports_download=False,
    )

  class ProjectsGroupsService(base_api.BaseApiService):
    """Service class for the projects_groups resource."""

    _NAME = u'projects_groups'

    def __init__(self, client):
      super(ClouderrorreportingV1beta1.ProjectsGroupsService, self).__init__(client)
      self._upload_configs = {
          }

    def Get(self, request, global_params=None):
      r"""Get the specified group.

      Args:
        request: (ClouderrorreportingProjectsGroupsGetRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (ErrorGroup) The response message.
      """
      config = self.GetMethodConfig('Get')
      return self._RunMethod(
          config, request, global_params=global_params)

    Get.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1beta1/projects/{projectsId}/groups/{groupsId}',
        http_method=u'GET',
        method_id=u'clouderrorreporting.projects.groups.get',
        ordered_params=[u'groupName'],
        path_params=[u'groupName'],
        query_params=[],
        relative_path=u'v1beta1/{+groupName}',
        request_field='',
        request_type_name=u'ClouderrorreportingProjectsGroupsGetRequest',
        response_type_name=u'ErrorGroup',
        supports_download=False,
    )

    def Update(self, request, global_params=None):
      r"""Replace the data for the specified group.
Fails if the group does not exist.

      Args:
        request: (ErrorGroup) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (ErrorGroup) The response message.
      """
      config = self.GetMethodConfig('Update')
      return self._RunMethod(
          config, request, global_params=global_params)

    Update.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1beta1/projects/{projectsId}/groups/{groupsId}',
        http_method=u'PUT',
        method_id=u'clouderrorreporting.projects.groups.update',
        ordered_params=[u'name'],
        path_params=[u'name'],
        query_params=[],
        relative_path=u'v1beta1/{+name}',
        request_field='<request>',
        request_type_name=u'ErrorGroup',
        response_type_name=u'ErrorGroup',
        supports_download=False,
    )

  class ProjectsService(base_api.BaseApiService):
    """Service class for the projects resource."""

    _NAME = u'projects'

    def __init__(self, client):
      super(ClouderrorreportingV1beta1.ProjectsService, self).__init__(client)
      self._upload_configs = {
          }

    def DeleteEvents(self, request, global_params=None):
      r"""Deletes all error events of a given project.

      Args:
        request: (ClouderrorreportingProjectsDeleteEventsRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (DeleteEventsResponse) The response message.
      """
      config = self.GetMethodConfig('DeleteEvents')
      return self._RunMethod(
          config, request, global_params=global_params)

    DeleteEvents.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1beta1/projects/{projectsId}/events',
        http_method=u'DELETE',
        method_id=u'clouderrorreporting.projects.deleteEvents',
        ordered_params=[u'projectName'],
        path_params=[u'projectName'],
        query_params=[],
        relative_path=u'v1beta1/{+projectName}/events',
        request_field='',
        request_type_name=u'ClouderrorreportingProjectsDeleteEventsRequest',
        response_type_name=u'DeleteEventsResponse',
        supports_download=False,
    )
