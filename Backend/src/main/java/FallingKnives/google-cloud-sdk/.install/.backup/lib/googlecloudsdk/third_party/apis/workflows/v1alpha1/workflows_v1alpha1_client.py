"""Generated client library for workflows version v1alpha1."""
# NOTE: This file is autogenerated and should not be edited by hand.
from apitools.base.py import base_api
from googlecloudsdk.third_party.apis.workflows.v1alpha1 import workflows_v1alpha1_messages as messages


class WorkflowsV1alpha1(base_api.BaseApiClient):
  """Generated client library for service workflows version v1alpha1."""

  MESSAGES_MODULE = messages
  BASE_URL = u'https://workflows.googleapis.com/'
  MTLS_BASE_URL = u'https://workflows.mtls.googleapis.com/'

  _PACKAGE = u'workflows'
  _SCOPES = [u'https://www.googleapis.com/auth/cloud-platform']
  _VERSION = u'v1alpha1'
  _CLIENT_ID = '1042881264118.apps.googleusercontent.com'
  _CLIENT_SECRET = 'x_Tw5K8nnjoRAqULM9PFAC2b'
  _USER_AGENT = u'google-cloud-sdk'
  _CLIENT_CLASS_NAME = u'WorkflowsV1alpha1'
  _URL_VERSION = u'v1alpha1'
  _API_KEY = None

  def __init__(self, url='', credentials=None,
               get_credentials=True, http=None, model=None,
               log_request=False, log_response=False,
               credentials_args=None, default_global_params=None,
               additional_http_headers=None, response_encoding=None):
    """Create a new workflows handle."""
    url = url or self.BASE_URL
    super(WorkflowsV1alpha1, self).__init__(
        url, credentials=credentials,
        get_credentials=get_credentials, http=http, model=model,
        log_request=log_request, log_response=log_response,
        credentials_args=credentials_args,
        default_global_params=default_global_params,
        additional_http_headers=additional_http_headers,
        response_encoding=response_encoding)
    self.projects_locations_operations = self.ProjectsLocationsOperationsService(self)
    self.projects_locations_workflows = self.ProjectsLocationsWorkflowsService(self)
    self.projects_locations = self.ProjectsLocationsService(self)
    self.projects = self.ProjectsService(self)

  class ProjectsLocationsOperationsService(base_api.BaseApiService):
    """Service class for the projects_locations_operations resource."""

    _NAME = u'projects_locations_operations'

    def __init__(self, client):
      super(WorkflowsV1alpha1.ProjectsLocationsOperationsService, self).__init__(client)
      self._upload_configs = {
          }

    def Cancel(self, request, global_params=None):
      r"""Starts asynchronous cancellation on a long-running operation.  The server.
makes a best effort to cancel the operation, but success is not
guaranteed.  If the server doesn't support this method, it returns
`google.rpc.Code.UNIMPLEMENTED`.  Clients can use
Operations.GetOperation or
other methods to check whether the cancellation succeeded or whether the
operation completed despite cancellation. On successful cancellation,
the operation is not deleted; instead, it becomes an operation with
an Operation.error value with a google.rpc.Status.code of 1,
corresponding to `Code.CANCELLED`.

      Args:
        request: (WorkflowsProjectsLocationsOperationsCancelRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (Empty) The response message.
      """
      config = self.GetMethodConfig('Cancel')
      return self._RunMethod(
          config, request, global_params=global_params)

    Cancel.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1alpha1/projects/{projectsId}/locations/{locationsId}/operations/{operationsId}:cancel',
        http_method=u'POST',
        method_id=u'workflows.projects.locations.operations.cancel',
        ordered_params=[u'name'],
        path_params=[u'name'],
        query_params=[],
        relative_path=u'v1alpha1/{+name}:cancel',
        request_field=u'cancelOperationRequest',
        request_type_name=u'WorkflowsProjectsLocationsOperationsCancelRequest',
        response_type_name=u'Empty',
        supports_download=False,
    )

    def Delete(self, request, global_params=None):
      r"""Deletes a long-running operation. This method indicates that the client is.
no longer interested in the operation result. It does not cancel the
operation. If the server doesn't support this method, it returns
`google.rpc.Code.UNIMPLEMENTED`.

      Args:
        request: (WorkflowsProjectsLocationsOperationsDeleteRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (Empty) The response message.
      """
      config = self.GetMethodConfig('Delete')
      return self._RunMethod(
          config, request, global_params=global_params)

    Delete.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1alpha1/projects/{projectsId}/locations/{locationsId}/operations/{operationsId}',
        http_method=u'DELETE',
        method_id=u'workflows.projects.locations.operations.delete',
        ordered_params=[u'name'],
        path_params=[u'name'],
        query_params=[],
        relative_path=u'v1alpha1/{+name}',
        request_field='',
        request_type_name=u'WorkflowsProjectsLocationsOperationsDeleteRequest',
        response_type_name=u'Empty',
        supports_download=False,
    )

    def Get(self, request, global_params=None):
      r"""Gets the latest state of a long-running operation.  Clients can use this.
method to poll the operation result at intervals as recommended by the API
service.

      Args:
        request: (WorkflowsProjectsLocationsOperationsGetRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (Operation) The response message.
      """
      config = self.GetMethodConfig('Get')
      return self._RunMethod(
          config, request, global_params=global_params)

    Get.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1alpha1/projects/{projectsId}/locations/{locationsId}/operations/{operationsId}',
        http_method=u'GET',
        method_id=u'workflows.projects.locations.operations.get',
        ordered_params=[u'name'],
        path_params=[u'name'],
        query_params=[],
        relative_path=u'v1alpha1/{+name}',
        request_field='',
        request_type_name=u'WorkflowsProjectsLocationsOperationsGetRequest',
        response_type_name=u'Operation',
        supports_download=False,
    )

    def List(self, request, global_params=None):
      r"""Lists operations that match the specified filter in the request. If the.
server doesn't support this method, it returns `UNIMPLEMENTED`.

NOTE: the `name` binding allows API services to override the binding
to use different resource name schemes, such as `users/*/operations`. To
override the binding, API services can add a binding such as
`"/v1/{name=users/*}/operations"` to their service configuration.
For backwards compatibility, the default name includes the operations
collection id, however overriding users must ensure the name binding
is the parent resource, without the operations collection id.

      Args:
        request: (WorkflowsProjectsLocationsOperationsListRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (ListOperationsResponse) The response message.
      """
      config = self.GetMethodConfig('List')
      return self._RunMethod(
          config, request, global_params=global_params)

    List.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1alpha1/projects/{projectsId}/locations/{locationsId}/operations',
        http_method=u'GET',
        method_id=u'workflows.projects.locations.operations.list',
        ordered_params=[u'name'],
        path_params=[u'name'],
        query_params=[u'filter', u'pageSize', u'pageToken'],
        relative_path=u'v1alpha1/{+name}/operations',
        request_field='',
        request_type_name=u'WorkflowsProjectsLocationsOperationsListRequest',
        response_type_name=u'ListOperationsResponse',
        supports_download=False,
    )

  class ProjectsLocationsWorkflowsService(base_api.BaseApiService):
    """Service class for the projects_locations_workflows resource."""

    _NAME = u'projects_locations_workflows'

    def __init__(self, client):
      super(WorkflowsV1alpha1.ProjectsLocationsWorkflowsService, self).__init__(client)
      self._upload_configs = {
          }

    def Create(self, request, global_params=None):
      r"""Creates a new workflow. If a workflow with the specified name already.
exists in the specified project and location, the long running operation
will return `ALREADY_EXISTS` error.

      Args:
        request: (WorkflowsProjectsLocationsWorkflowsCreateRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (Operation) The response message.
      """
      config = self.GetMethodConfig('Create')
      return self._RunMethod(
          config, request, global_params=global_params)

    Create.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1alpha1/projects/{projectsId}/locations/{locationsId}/workflows',
        http_method=u'POST',
        method_id=u'workflows.projects.locations.workflows.create',
        ordered_params=[u'parent'],
        path_params=[u'parent'],
        query_params=[u'workflowId'],
        relative_path=u'v1alpha1/{+parent}/workflows',
        request_field=u'workflow',
        request_type_name=u'WorkflowsProjectsLocationsWorkflowsCreateRequest',
        response_type_name=u'Operation',
        supports_download=False,
    )

    def Delete(self, request, global_params=None):
      r"""Deletes a workflow with the specified name.

      Args:
        request: (WorkflowsProjectsLocationsWorkflowsDeleteRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (Operation) The response message.
      """
      config = self.GetMethodConfig('Delete')
      return self._RunMethod(
          config, request, global_params=global_params)

    Delete.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1alpha1/projects/{projectsId}/locations/{locationsId}/workflows/{workflowsId}',
        http_method=u'DELETE',
        method_id=u'workflows.projects.locations.workflows.delete',
        ordered_params=[u'name'],
        path_params=[u'name'],
        query_params=[],
        relative_path=u'v1alpha1/{+name}',
        request_field='',
        request_type_name=u'WorkflowsProjectsLocationsWorkflowsDeleteRequest',
        response_type_name=u'Operation',
        supports_download=False,
    )

    def Get(self, request, global_params=None):
      r"""Gets details of a single Workflow.

      Args:
        request: (WorkflowsProjectsLocationsWorkflowsGetRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (Workflow) The response message.
      """
      config = self.GetMethodConfig('Get')
      return self._RunMethod(
          config, request, global_params=global_params)

    Get.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1alpha1/projects/{projectsId}/locations/{locationsId}/workflows/{workflowsId}',
        http_method=u'GET',
        method_id=u'workflows.projects.locations.workflows.get',
        ordered_params=[u'name'],
        path_params=[u'name'],
        query_params=[],
        relative_path=u'v1alpha1/{+name}',
        request_field='',
        request_type_name=u'WorkflowsProjectsLocationsWorkflowsGetRequest',
        response_type_name=u'Workflow',
        supports_download=False,
    )

    def List(self, request, global_params=None):
      r"""Lists Workflows in a given project and location.

      Args:
        request: (WorkflowsProjectsLocationsWorkflowsListRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (ListWorkflowsResponse) The response message.
      """
      config = self.GetMethodConfig('List')
      return self._RunMethod(
          config, request, global_params=global_params)

    List.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1alpha1/projects/{projectsId}/locations/{locationsId}/workflows',
        http_method=u'GET',
        method_id=u'workflows.projects.locations.workflows.list',
        ordered_params=[u'parent'],
        path_params=[u'parent'],
        query_params=[u'filter', u'orderBy', u'pageSize', u'pageToken'],
        relative_path=u'v1alpha1/{+parent}/workflows',
        request_field='',
        request_type_name=u'WorkflowsProjectsLocationsWorkflowsListRequest',
        response_type_name=u'ListWorkflowsResponse',
        supports_download=False,
    )

    def Patch(self, request, global_params=None):
      r"""Updates existing workflow and increases its.
version_id.
Has no impact on any workflow execution.

      Args:
        request: (WorkflowsProjectsLocationsWorkflowsPatchRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (Operation) The response message.
      """
      config = self.GetMethodConfig('Patch')
      return self._RunMethod(
          config, request, global_params=global_params)

    Patch.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1alpha1/projects/{projectsId}/locations/{locationsId}/workflows/{workflowsId}',
        http_method=u'PATCH',
        method_id=u'workflows.projects.locations.workflows.patch',
        ordered_params=[u'name'],
        path_params=[u'name'],
        query_params=[u'updateMask'],
        relative_path=u'v1alpha1/{+name}',
        request_field=u'workflow',
        request_type_name=u'WorkflowsProjectsLocationsWorkflowsPatchRequest',
        response_type_name=u'Operation',
        supports_download=False,
    )

  class ProjectsLocationsService(base_api.BaseApiService):
    """Service class for the projects_locations resource."""

    _NAME = u'projects_locations'

    def __init__(self, client):
      super(WorkflowsV1alpha1.ProjectsLocationsService, self).__init__(client)
      self._upload_configs = {
          }

    def Get(self, request, global_params=None):
      r"""Gets information about a location.

      Args:
        request: (WorkflowsProjectsLocationsGetRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (Location) The response message.
      """
      config = self.GetMethodConfig('Get')
      return self._RunMethod(
          config, request, global_params=global_params)

    Get.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1alpha1/projects/{projectsId}/locations/{locationsId}',
        http_method=u'GET',
        method_id=u'workflows.projects.locations.get',
        ordered_params=[u'name'],
        path_params=[u'name'],
        query_params=[],
        relative_path=u'v1alpha1/{+name}',
        request_field='',
        request_type_name=u'WorkflowsProjectsLocationsGetRequest',
        response_type_name=u'Location',
        supports_download=False,
    )

    def List(self, request, global_params=None):
      r"""Lists information about the supported locations for this service.

      Args:
        request: (WorkflowsProjectsLocationsListRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (ListLocationsResponse) The response message.
      """
      config = self.GetMethodConfig('List')
      return self._RunMethod(
          config, request, global_params=global_params)

    List.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1alpha1/projects/{projectsId}/locations',
        http_method=u'GET',
        method_id=u'workflows.projects.locations.list',
        ordered_params=[u'name'],
        path_params=[u'name'],
        query_params=[u'filter', u'pageSize', u'pageToken'],
        relative_path=u'v1alpha1/{+name}/locations',
        request_field='',
        request_type_name=u'WorkflowsProjectsLocationsListRequest',
        response_type_name=u'ListLocationsResponse',
        supports_download=False,
    )

  class ProjectsService(base_api.BaseApiService):
    """Service class for the projects resource."""

    _NAME = u'projects'

    def __init__(self, client):
      super(WorkflowsV1alpha1.ProjectsService, self).__init__(client)
      self._upload_configs = {
          }
