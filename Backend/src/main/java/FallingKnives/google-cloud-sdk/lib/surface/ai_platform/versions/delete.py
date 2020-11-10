# -*- coding: utf-8 -*- #
# Copyright 2019 Google LLC. All Rights Reserved.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
"""ai-platform versions delete command."""

from __future__ import absolute_import
from __future__ import division
from __future__ import unicode_literals

from googlecloudsdk.api_lib.ml_engine import operations
from googlecloudsdk.api_lib.ml_engine import versions_api
from googlecloudsdk.calliope import base
from googlecloudsdk.command_lib.ml_engine import endpoint_util
from googlecloudsdk.command_lib.ml_engine import flags
from googlecloudsdk.command_lib.ml_engine import versions_util


def _AddDeleteArgs(parser, hide_region_arg=True):
  flags.GetModelName(positional=False, required=True).AddToParser(parser)
  flags.GetRegionArg(hidden=hide_region_arg).AddToParser(parser)
  flags.VERSION_NAME.AddToParser(parser)


def _Run(args):
  with endpoint_util.MlEndpointOverrides(region=args.region):
    client = versions_api.VersionsClient()
    return versions_util.Delete(
        client, operations.OperationsClient(), args.version, model=args.model)


@base.ReleaseTracks(base.ReleaseTrack.GA)
class Delete(base.DeleteCommand):
  """Delete an existing AI Platform version."""

  @staticmethod
  def Args(parser):
    _AddDeleteArgs(parser)

  def Run(self, args):
    return _Run(args)


@base.ReleaseTracks(base.ReleaseTrack.BETA, base.ReleaseTrack.ALPHA)
class DeleteBeta(base.DeleteCommand):
  """Delete an existing AI Platform version."""

  @staticmethod
  def Args(parser):
    _AddDeleteArgs(parser, hide_region_arg=False)

  def Run(self, args):
    return _Run(args)