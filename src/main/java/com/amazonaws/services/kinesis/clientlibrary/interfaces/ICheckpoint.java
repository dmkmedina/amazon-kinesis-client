/*
 * Copyright 2012-2013 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Amazon Software License (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 * http://aws.amazon.com/asl/
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.amazonaws.services.kinesis.clientlibrary.interfaces;

import com.amazonaws.services.kinesis.clientlibrary.exceptions.KinesisClientLibException;

/**
 * Interface for checkpoint trackers.
 */
public interface ICheckpoint {

    /**
     * Record a checkpoint for a shard (e.g. sequence number of last record processed by application).
     * Upon failover, record processing is resumed from this point.
     * 
     * @param shardId Checkpoint is specified for this shard.
     * @param checkpointValue Value of the checkpoint (e.g. Kinesis sequence number)
     * @param concurrencyToken Used with conditional writes to prevent stale updates
     *        (e.g. if there was a fail over to a different record processor, we don't want to 
     *        overwrite it's checkpoint)
     * @throws KinesisClientLibException Thrown if we were unable to save the checkpoint
     */
    void setCheckpoint(String shardId, String checkpointValue, String concurrencyToken)
        throws KinesisClientLibException;

    /**
     * Get the current checkpoint stored for the specified shard. Useful for checking that the parent shard
     * has been completely processed before we start processing the child shard.
     * 
     * @param shardId Current checkpoint for this shard is fetched
     * @return Current checkpoint for this shard, null if there is no record for this shard.
     * @throws KinesisClientLibException Thrown if we are unable to fetch the checkpoint
     */
    String getCheckpoint(String shardId) throws KinesisClientLibException;

}
