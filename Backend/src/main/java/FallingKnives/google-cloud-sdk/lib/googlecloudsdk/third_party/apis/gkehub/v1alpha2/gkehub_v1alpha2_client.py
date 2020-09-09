"""Generated client library for gkehub version v1alpha2."""
# NOTE: This file is autogenerated and should not be edited by hand.
from apitools.base.py import base_api
from googlecloudsdk.third_party.apis.gkehub.v1alpha2 import gkehub_v1alpha2_messages as messages


class GkehubV1alpha2(base_api.BaseApiClient):
  """Generated client library for service gkehub version v1alpha2."""

  MESSAGES_MODULE = messages
  BASE_URL = u'https://gkehub.googleapis.com/'
  MTLS_BASE_URL = u'https://gkehub.mtls.googleapis.com/'

  _PACKAGE = u'gkehub'
  _SCOPES = [u'https://www.googleapis.com/auth/cloud-platform']
  _VERSION = u'v1alpha2'
  _CLIENT_ID = '1042881264118.apps.googleusercontent.com'
  _CLIENT_SECRET = 'x_Tw5K8nnjoRAqULM9PFAC2b'
  _USER_AGENT = u'google-cloud-sdk'
  _CLIENT_CLASS_NAME = u'GkehubV1alpha2'
  _URL_VERSION = u'v1alpha2'
  _API_KEY = None

  def __init__(self, url='', credentials=None,
               get_credentials=True, http=None, model=None,
               log_request=False, log_response=False,
               credentials_args=None, default_global_params=None,
               additional_http_headers=None, response_encoding=None):
    """Create a new gkehub handle."""
    url = url or self.BASE_URL
    super(GkehubV1alpha2, self).__init__(
        url, credentials=credentials,
        get_credentials=get_credentials, http=http, model=model,
        log_request=log_request, log_response=log_response,
        credentials_args=credentials_args,
        default_global_params=default_global_params,
        additional_http_headers=additional_http_headers,
        response_encoding=response_encoding)
    self.projects_locations_memberships = self.ProjectsLocationsMembershipsService(self)
    self.projects_locations_operations = self.ProjectsLocationsOperationsService(self)
    self.projects_locations = self.ProjectsLocationsService(self)
    self.projects = self.ProjectsService(self)

  class ProjectsLocationsMembershipsService(base_api.BaseApiService):
    """Service class for the projects_locations_memberships resource."""

    _NAME = u'projects_locations_memberships'

    def __init__(self, client):
      super(GkehubV1alpha2.ProjectsLocationsMembershipsService, self).__init__(client)
      self._upload_configs = {
          }

    def Create(self, request, global_params=None):
      r"""Adds a new Membership.

      Args:
        request: (GkehubProjectsLocationsMembershipsCreateRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (Operation) The response message.
      """
      config = self.GetMethodConfig('Create')
      return self._RunMethod(
          config, request, global_params=global_params)

    Create.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1alpha2/projects/{projectsId}/locations/{locationsId}/memberships',
        http_method=u'POST',
        method_id=u'gkehub.projects.locations.memberships.create',
        ordered_params=[u'parent'],
        path_params=[u'parent'],
        query_params=[u'membershipId'],
        relative_path=u'v1alpha2/{+parent}/memberships',
        request_field=u'membership',
        request_type_name=u'GkehubProjectsLocationsMembershipsCreateRequest',
        response_type_name=u'Operation',
        supports_download=False,
    )

    def Delete(self, request, global_params=None):
      r"""Removes a single Membership.

      Args:
        request: (GkehubProjectsLocationsMembershipsDeleteRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (Operation) The response message.
      """
      config = self.GetMethodConfig('Delete')
      return self._RunMethod(
          config, request, global_params=global_params)

    Delete.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1alpha2/projects/{projectsId}/locations/{locationsId}/memberships/{membershipsId}',
        http_method=u'DELETE',
        method_id=u'gkehub.projects.locations.memberships.delete',
        ordered_params=[u'name'],
        path_params=[u'name'],
        query_params=[],
        relative_path=u'v1alpha2/{+name}',
        request_field='',
        request_type_name=u'GkehubProjectsLocationsMembershipsDeleteRequest',
        response_type_name=u'Operation',
        supports_download=False,
    )

    def GenerateConnectManifest(self, request, global_params=None):
      r"""Generate the manifest for deployment of GKE connect agent.

      Args:
        request: (GkehubProjectsLocationsMembershipsGenerateConnectManifestRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (GenerateConnectManifestResponse) The response message.
      """
      config = self.GetMethodConfig('GenerateConnectManifest')
      return self._RunMethod(
          config, request, global_params=global_params)

    GenerateConnectManifest.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1alpha2/projects/{projectsId}/locations/{locationsId}/memberships/{membershipsId}:generateConnectManifest',
        http_method=u'GET',
        method_id=u'gkehub.projects.locations.memberships.generateConnectManifest',
        ordered_params=[u'name'],
        path_params=[u'name'],
        query_params=[u'imagePullSecretContent', u'isUpgrade', u'namespace', u'proxy', u'registry', u'version'],
        relative_path=u'v1alpha2/{+name}:generateConnectManifest',
        request_field='',
        request_type_name=u'GkehubProjectsLocationsMembershipsGenerateConnectManifestRequest',
        response_type_name=u'GenerateConnectManifestResponse',
        supports_download=False,
    )

    def Get(self, request, global_params=None):
      r"""Gets details of a single Membership.

      Args:
        request: (GkehubProjectsLocationsMembershipsGetRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (Membership) The response message.
      """
      config = self.GetMethodConfig('Get')
      return self._RunMethod(
          config, request, global_params=global_params)

    Get.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1alpha2/projects/{projectsId}/locations/{locationsId}/memberships/{membershipsId}',
        http_method=u'GET',
        method_id=u'gkehub.projects.locations.memberships.get',
        ordered_params=[u'name'],
        path_params=[u'name'],
        query_params=[],
        relative_path=u'v1alpha2/{+name}',
        request_field='',
        request_type_name=u'GkehubProjectsLocationsMembershipsGetRequest',
        response_type_name=u'Membership',
        supports_download=False,
    )

    def GetIamPolicy(self, request, global_params=None):
      r"""Gets the access control policy for a resource.
Returns an empty policy if the resource exists and does not have a policy
set.

      Args:
        request: (GkehubProjectsLocationsMembershipsGetIamPolicyRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (Policy) The response message.
      """
      config = self.GetMethodConfig('GetIamPolicy')
      return self._RunMethod(
          config, request, global_params=global_params)

    GetIamPolicy.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1alpha2/projects/{projectsId}/locations/{locationsId}/memberships/{membershipsId}:getIamPolicy',
        http_method=u'GET',
        method_id=u'gkehub.projects.locations.memberships.getIamPolicy',
        ordered_params=[u'resource'],
        path_params=[u'resource'],
        query_params=[u'options_requestedPolicyVersion'],
        relative_path=u'v1alpha2/{+resource}:getIamPolicy',
        request_field='',
        request_type_name=u'GkehubProjectsLocationsMembershipsGetIamPolicyRequest',
        response_type_name=u'Policy',
        supports_download=False,
    )

    def List(self, request, global_params=None):
      r"""Lists Memberships in a given project and location.

      Args:
        request: (GkehubProjectsLocationsMembershipsListRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (ListMembershipsResponse) The response message.
      """
      config = self.GetMethodConfig('List')
      return self._RunMethod(
          config, request, global_params=global_params)

    List.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1alpha2/projects/{projectsId}/locations/{locationsId}/memberships',
        http_method=u'GET',
        method_id=u'gkehub.projects.locations.memberships.list',
        ordered_params=[u'parent'],
        path_params=[u'parent'],
        query_params=[u'filter', u'orderBy', u'pageSize', u'pageToken'],
        relative_path=u'v1alpha2/{+parent}/memberships',
        request_field='',
        request_type_name=u'GkehubProjectsLocationsMembershipsListRequest',
        response_type_name=u'ListMembershipsResponse',
        supports_download=False,
    )

    def Patch(self, request, global_params=None):
      r"""Updates an existing Membership.

      Args:
        request: (GkehubProjectsLocationsMembershipsPatchRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (Operation) The response message.
      """
      config = self.GetMethodConfig('Patch')
      return self._RunMethod(
          config, request, global_params=global_params)

    Patch.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1alpha2/projects/{projectsId}/locations/{locationsId}/memberships/{membershipsId}',
        http_method=u'PATCH',
        method_id=u'gkehub.projects.locations.memberships.patch',
        ordered_params=[u'name'],
        path_params=[u'name'],
        query_params=[u'updateMask'],
        relative_path=u'v1alpha2/{+name}',
        request_field=u'membership',
        request_type_name=u'GkehubProjectsLocationsMembershipsPatchRequest',
        response_type_name=u'Operation',
        supports_download=False,
    )

    def SetIamPolicy(self, request, global_params=None):
      r"""Sets the access control policy on the specified resource. Replaces any.
existing policy.

Can return Public Errors: NOT_FOUND, INVALID_ARGUMENT and PERMISSION_DENIED

      Args:
        request: (GkehubProjectsLocationsMembershipsSetIamPolicyRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (Policy) The response message.
      """
      config = self.GetMethodConfig('SetIamPolicy')
      return self._RunMethod(
          config, request, global_params=global_params)

    SetIamPolicy.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1alpha2/projects/{projectsId}/locations/{locationsId}/memberships/{membershipsId}:setIamPolicy',
        http_method=u'POST',
        method_id=u'gkehub.projects.locations.memberships.setIamPolicy',
        ordered_params=[u'resource'],
        path_params=[u'resource'],
        query_params=[],
        relative_path=u'v1alpha2/{+resource}:setIamPolicy',
        request_field=u'setIamPolicyRequest',
        request_type_name=u'GkehubProjectsLocationsMembershipsSetIamPolicyRequest',
        response_type_name=u'Policy',
        supports_download=False,
    )

    def TestIamPermissions(self, request, global_params=None):
      r"""Returns permissions that a caller has on the specified resource.
If the resource does not exist, this will return an empty set of
permissions, not a NOT_FOUND error.

Note: This operation is designed to be used for building permission-aware
UIs and command-line tools, not for authorization checking. This operation
may "fail open" without warning.

      Args:
        request: (GkehubProjectsLocationsMembershipsTestIamPermissionsRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (TestIamPermissionsResponse) The response message.
      """
      config = self.GetMethodConfig('TestIamPermissions')
      return self._RunMethod(
          config, request, global_params=global_params)

    TestIamPermissions.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1alpha2/projects/{projectsId}/locations/{locationsId}/memberships/{membershipsId}:testIamPermissions',
        http_method=u'POST',
        method_id=u'gkehub.projects.locations.memberships.testIamPermissions',
        ordered_params=[u'resource'],
        path_params=[u'resource'],
        query_params=[],
        relative_path=u'v1alpha2/{+resource}:testIamPermissions',
        request_field=u'testIamPermissionsRequest',
        request_type_name=u'GkehubProjectsLocationsMembershipsTestIamPermissionsRequest',
        response_type_name=u'TestIamPermissionsResponse',
        supports_download=False,
    )

  class ProjectsLocationsOperationsService(base_api.BaseApiService):
    """Service class for the projects_locations_operations resource."""

    _NAME = u'projects_locations_operations'

    def __init__(self, client):
      super(GkehubV1alpha2.ProjectsLocationsOperationsService, self).__init__(client)
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
        request: (GkehubProjectsLocationsOperationsCancelRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (Empty) The response message.
      """
      config = self.GetMethodConfig('Cancel')
      return self._RunMethod(
          config, request, global_params=global_params)

    Cancel.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1alpha2/projects/{projectsId}/locations/{locationsId}/operations/{operationsId}:cancel',
        http_method=u'POST',
        method_id=u'gkehub.projects.locations.operations.cancel',
        ordered_params=[u'name'],
        path_params=[u'name'],
        query_params=[],
        relative_path=u'v1alpha2/{+name}:cancel',
        request_field=u'cancelOperationRequest',
        request_type_name=u'GkehubProjectsLocationsOperationsCancelRequest',
        response_type_name=u'Empty',
        supports_download=False,
    )

    def Delete(self, request, global_params=None):
      r"""Deletes a long-running operation. This method indicates that the client is.
no longer interested in the operation result. It does not cancel the
operation. If the server doesn't support this method, it returns
`google.rpc.Code.UNIMPLEMENTED`.

      Args:
        request: (GkehubProjectsLocationsOperationsDeleteRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (Empty) The response message.
      """
      config = self.GetMethodConfig('Delete')
      return self._RunMethod(
          config, request, global_params=global_params)

    Delete.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1alpha2/projects/{projectsId}/locations/{locationsId}/operations/{operationsId}',
        http_method=u'DELETE',
        method_id=u'gkehub.projects.locations.operations.delete',
        ordered_params=[u'name'],
        path_params=[u'name'],
        query_params=[],
        relative_path=u'v1alpha2/{+name}',
        request_field='',
        request_type_name=u'GkehubProjectsLocationsOperationsDeleteRequest',
        response_type_name=u'Empty',
        supports_download=False,
    )

    def Get(self, request, global_params=None):
      r"""Gets the latest state of a long-running operation.  Clients can use this.
method to poll the operation result at intervals as recommended by the API
service.

      Args:
        request: (GkehubProjectsLocationsOperationsGetRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (Operation) The response message.
      """
      config = self.GetMethodConfig('Get')
      return self._RunMethod(
          config, request, global_params=global_params)

    Get.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1alpha2/projects/{projectsId}/locations/{locationsId}/operations/{operationsId}',
        http_method=u'GET',
        method_id=u'gkehub.projects.locations.operations.get',
        ordered_params=[u'name'],
        path_params=[u'name'],
        query_params=[],
        relative_path=u'v1alpha2/{+name}',
        request_field='',
        request_type_name=u'GkehubProjectsLocationsOperationsGetRequest',
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
        request: (GkehubProjectsLocationsOperationsListRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (ListOperationsResponse) The response message.
      """
      config = self.GetMethodConfig('List')
      return self._RunMethod(
          config, request, global_params=global_params)

    List.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1alpha2/projects/{projectsId}/locations/{locationsId}/operations',
        http_method=u'GET',
        method_id=u'gkehub.projects.locations.operations.list',
        ordered_params=[u'name'],
        path_params=[u'name'],
        query_params=[u'filter', u'pageSize', u'pageToken'],
        relative_path=u'v1alpha2/{+name}/operations',
        request_field='',
        request_type_name=u'GkehubProjectsLocationsOperationsListRequest',
        response_type_name=u'ListOperationsResponse',
        supports_download=False,
    )

  class ProjectsLocationsService(base_api.BaseApiService):
    """Service class for the projects_locations resource."""

    _NAME = u'projects_locations'

    def __init__(self, client):
      super(GkehubV1alpha2.ProjectsLocationsService, self).__init__(client)
      self._upload_configs = {
          }

    def Get(self, request, global_params=None):
      r"""Gets information about a location.

      Args:
        request: (GkehubProjectsLocationsGetRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (Location) The response message.
      """
      config = self.GetMethodConfig('Get')
      return self._RunMethod(
          config, request, global_params=global_params)

    Get.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1alpha2/projects/{projectsId}/locations/{locationsId}',
        http_method=u'GET',
        method_id=u'gkehub.projects.locations.get',
        ordered_params=[u'name'],
        path_params=[u'name'],
        query_params=[],
        relative_path=u'v1alpha2/{+name}',
        request_field='',
        request_type_name=u'GkehubProjectsLocationsGetRequest',
        response_type_name=u'Location',
        supports_download=False,
    )

    def List(self, request, global_params=None):
      r"""Lists information about the supported locations for this service.

      Args:
        request: (GkehubProjectsLocationsListRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (ListLocationsResponse) The response message.
      """
      config = self.GetMethodConfig('List')
      return self._RunMethod(
          config, request, global_params=global_params)

    List.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1alpha2/projects/{projectsId}/locations',
        http_method=u'GET',
        method_id=u'gkehub.projects.locations.list',
        ordered_params=[u'name'],
        path_params=[u'name'],
        query_params=[u'filter', u'includeUnrevealedLocations', u'pageSize', u'pageToken'],
        relative_path=u'v1alpha2/{+name}/locations',
        request_field='',
        request_type_name=u'GkehubProjectsLocationsListRequest',
        response_type_name=u'ListLocationsResponse',
        supports_download=False,
    )

  class ProjectsService(base_api.BaseApiService):
    """Service class for the projects resource."""

    _NAME = u'projects'

    def __init__(self, client):
      super(GkehubV1alpha2.ProjectsService, self).__init__(client)
      self._upload_configs = {
          }
