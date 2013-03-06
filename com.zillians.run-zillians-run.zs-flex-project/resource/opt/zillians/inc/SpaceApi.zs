/**
 * Zillians MMO
 * Copyright (C) 2007-2010 Zillians.com, Inc.
 * For more information see http://www.zillians.com
 *
 * Zillians MMO is the library and runtime for massive multiplayer online game
 * development in utility computing model, which runs as a service for every
 * developer to build their virtual world running on our GPU-assisted machines.
 *
 * This is a close source library intended to be used solely within Zillians.com
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * COPYRIGHT HOLDER(S) BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN
 * AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
/**
 * @date Jul 15, 2010 sdk - Initial version created.
 */

/**
 * @file
 * SpaceApi provides simple ways to perform efficient spatial query. Spatial query is
 * crucial in most online game since thousands of players interact concurrently in the
 * same virtual world and it's basically impossible to send everything to everyone, so
 * we need a way to filter those updates in order to prevent players from flooding.
 *
 * To elaborate, the major concept is "space" and "object". A "space" is basically just
 * a bounded space, parameterized by its dimension and size. On the other hand, an "object"
 * is a wrapper object containing a reference to arbitrary game object and also some
 * spatial information, and an "object" can reside in a "space" of corresponding type.
 *
 * Currently we have five different types of space: Grid2D, Grid3D, Quadtree, Octtree, KNN.
 * Each space is managed and characterized differently. For example, Grid2D space
 * (GridSpace2D) can be regarded as a two-dimension grid of cells in which multiple
 * objects of corresponding type (GridObject2D) can resides. Grid3D is just an extension
 * to Grid2D space with additional dimension.
 *
 * For Quadtree and Octtree space, they are conventional data structure usually seen in computer
 * graphics to organize objects in spatially-aware order and to accelerate neighborhood
 * query.
 *
 * KNN space, as its name implies, they are implmented to provide fastest K-nearest-neighbor
 * search where K is basically a constant for all queries.
 *
 * A basic usage scenario would be creating "space" first by createXXXSpace() API, then
 * create "object" of corresponding type by simply "new" the XXX object, and finally call
 * addXXXObject() to the space we just created, and we're done. After that we can perform
 * asynchronous spatial search by calling searchXXX() with the "object" we're looking at.
 *
 */

package zillians.api.space
{
    //function getMaximumMapCount():uint32;

    //////////////////////////////////////////////////////////////////////
    /// Grid2D

    //@nonpersistent
    //@type_id { value = 0; }
    @native
    object GridSpace2D
    {
        var accel:uint64;

        var world_x:float32;
        var world_y:float32;
        var grid_x:uint32;
        var grid_y:uint32;
    }

    //@nonpersistent
    //@type_id { value = 1; }
    @native
    object GridObject2D
    {
        var space_ref:object;

        var object_ref:object;
        var world_x:float32;
        var world_y:float32;
        var grid_x:uint32;
        var grid_y:uint32;
    }

    // space
    @function_mapper { name="__zillians_api_space_createSpaceObjectGrid2D"; }
    function createSpaceObjectGrid2D(
        var world_x:float32,
        var world_y:float32,
        var grid_x:uint32,
        var grid_y:uint32):GridSpace2D;

    @function_mapper { name="__zillians_api_space_destroySpaceObjectGrid2D"; }
    function destroySpaceObjectGrid2D(
        var space_object:GridSpace2D):void;

    // wrapper_object
    @function_mapper { name="__zillians_api_space_createWrapperObjectGrid2D"; }
    function createWrapperObjectGrid2D(
        var game_object_ref:object):GridObject2D;

    @function_mapper { name="__zillians_api_space_destroyWrapperObjectGrid2D"; }
    function destroyWrapperObjectGrid2D(
        var wrapper_object:GridObject2D):void;

    @function_mapper { name="__zillians_api_space_getGameObjectReferenceInWrapperObjectGrid2D"; }
    function getGameObjectReferenceInWrapperObjectGrid2D(
        var wrapper_object:GridObject2D):object;

    @function_mapper { name="__zillians_api_space_setGameObjectReferenceInWrapperObjectGrid2D"; }
    function setGameObjectReferenceInWrapperObjectGrid2D(
        var wrapper_object:GridObject2D,
        var game_object_ref:object):void;

    // wrapper_object -- add/remove
    @function_mapper { name="__zillians_api_space_addGrid2D"; }
    function addGrid2D(
        var wrapper_object:GridObject2D,
        var space_object:GridSpace2D,
        var world_x:float32,
        var world_y:float32):void;

    @function_mapper { name="__zillians_api_space_addGrid2DAsync"; }
    function addGrid2DAsync(
        var wrapper_object:GridObject2D,
        var space_object:GridSpace2D,
        var world_x:float32,
        var world_y:float32,
        var callback:function(GridSpace2D):void):void;

    @function_mapper { name="__zillians_api_space_removeGrid2D"; }
    function removeGrid2D(
        var wrapper_object:GridObject2D):void;

    @function_mapper { name="__zillians_api_space_removeGrid2DAsync"; }
    function removeGrid2DAsync(
        var wrapper_object:GridObject2D,
        var callback:function(GridObject2D):void):void;

    // wrapper_object -- modify/query
    @function_mapper { name="__zillians_api_space_moveGrid2D"; }
    function moveGrid2D(
        var wrapper_object:GridObject2D,
        var world_x:float32,
        var world_y:float32):void;

    @function_mapper { name="__zillians_api_space_moveGrid2DAsync"; }
    function moveGrid2DAsync(
        var wrapper_object:GridObject2D,
        var world_x:float32,
        var world_y:float32,
        var callback:function(GridObject2D):void):void;

    //@function_mapper { name="__zillians_api_space_searchGrid2D"; }
    //function searchGrid2D(
    //    var wrapper_object:GridObject2D,
    //    /* need array or list implementation here */):void;

    @function_mapper { name="__zillians_api_space_searchGrid2DAsync"; }
    function searchGrid2DAsync(
        var wrapper_object:GridObject2D,
        var callback:function(GridObject2D, GridObject2D):void):void;

    @function_mapper { name="__zillians_api_space_searchGrid2DExplicitAsync"; }
    function searchGrid2DExplicitAsync(
        var space_object:GridSpace2D,
        var grid_x:uint32, var grid_y:uint32,
        var callback:function(GridObject2D, GridObject2D):void):void;

    @function_mapper { name="__zillians_api_space_searchGrid2DWithLimitAsync"; }
    function searchGrid2DWithLimitAsync(
        var wrapper_object:GridObject2D,
        var max_neighbor_count:uint32,
        var callback:function(GridObject2D, GridObject2D):void):void;

    @function_mapper { name="__zillians_api_space_searchGrid2DWithFilterAsync"; }
    function searchGrid2DWithFilterAsync(
        var wrapper_object:GridObject2D,
        var filter_distance_min:float32,
        var filter_distance_max:float32,
        var callback:function(GridObject2D, GridObject2D):void):void;

    @function_mapper { name="__zillians_api_space_searchGrid2DWithFilterWithLimitAsync"; }
    function searchGrid2DWithFilterWithLimitAsync(
        var wrapper_object:GridObject2D,
        var filter_distance_min:float32,
        var filter_distance_max:float32,
        var max_neighbor_count:uint32,
        var callback:function(GridObject2D, GridObject2D):void):void;

    // other
    @function_mapper { name="__zillians_api_space_getMaxGrid2DSizeX"; }
    function getMaxGrid2DSizeX():uint32;

    @function_mapper { name="__zillians_api_space_getMaxGrid2DSizeY"; }
    function getMaxGrid2DSizeY():uint32;

    @function_mapper { name="__zillians_api_space_getMaxGrid2D"; }
    function getMaxGrid2D():uint32;

    //////////////////////////////////////////////////////////////////////
    /// KNN2D

    object KNNSpace2D
    { }

    /**
    * @brief blah
    * @class KNNObject2D blah
    * @param ref blah
    * @param x blah
    * @param y blah
    * @param z blah
    */
    object KNNObject2D
    {
        @hidden var space_id:object;

        var ref:object;
        var x:float32;
        var y:float32;
    }

    // space
    @function_mapper { name="__zillians_api_space_createSpaceObjectKNN2D"; }
    function createSpaceObjectKNN2D(
        var world_x:float32,
        var world_y:float32,
        var max_object_count:uint32):KNNSpace2D;

    @function_mapper { name="__zillians_api_space_destroySpaceObjectKNN2D"; }
    function destroySpaceObjectKNN2D(
        var space_object:KNNSpace2D):void;

    // wrapper_object
    @function_mapper { name="__zillians_api_space_createWrapperObjectKNN2D"; }
    function createWrapperObjectKNN2D(
        var game_object_ref:object):KNNObject2D;

    @function_mapper { name="__zillians_api_space_destroyWrapperObjectKNN2D"; }
    function destroyWrapperObjectKNN2D(
        var wrapper_object:KNNObject2D):void;

    @function_mapper { name="__zillians_api_space_getGameObjectReferenceInWrapperObjectKNN2D"; }
    function getGameObjectReferenceInWrapperObjectKNN2D(
        var wrapper_object:KNNObject2D):object;

    @function_mapper { name="__zillians_api_space_setGameObjectReferenceInWrapperObjectKNN2D"; }
    function setGameObjectReferenceInWrapperObjectKNN2D(
        var wrapper_object:KNNObject2D,
        var game_object_ref:object):void;

    // wrapper_object -- add/remove
    @function_mapper { name="__zillians_api_space_addKNN2D"; }
    function addKNN2D(
        var wrapper_object:KNNObject2D,
        var space_object:KNNSpace2D,
        var world_x:float32,
        var world_y:float32):void;

    @function_mapper { name="__zillians_api_space_addKNN2DAsync"; }
    function addKNN2DAsync(
        var wrapper_object:KNNObject2D,
        var space_object:KNNSpace2D,
        var world_x:float32,
        var world_y:float32,
        var callback:function(KNNSpace2D):void):void;

    @function_mapper { name="__zillians_api_space_removeKNN2D"; }
    function removeKNN2D(
        var wrapper_object:KNNObject2D):void;

    @function_mapper { name="__zillians_api_space_removeKNN2DAsync"; }
    function removeKNN2DAsync(
        var wrapper_object:KNNObject2D,
        var callback:function(KNNObject2D):void):void;

    // wrapper_object -- modify/query
    @function_mapper { name="__zillians_api_space_moveKNN2D"; }
    function moveKNN2D(
        var wrapper_object:KNNObject2D,
        var world_x:float32,
        var world_y:float32):void;

    @function_mapper { name="__zillians_api_space_moveKNN2DAsync"; }
    function moveKNN2DAsync(
        var wrapper_object:KNNObject2D,
        var world_x:float32,
        var world_y:float32,
        var callback:function(KNNObject2D):void):void;

    //@function_mapper { name="__zillians_api_space_searchKNN2D"; }
    //function searchKNN2D(
    //    var wrapper_object:KNNObject2D,
    //    /* need array or list implementation here */):void;

    @function_mapper { name="__zillians_api_space_searchKNN2DAsync"; }
    function searchKNN2DAsync(
        var wrapper_object:KNNObject2D,
        var radius_squared:float32,
        var max_neighbor_count:uint32,
        var callback:function(KNNObject2D, KNNObject2D):void):void;

    // other
    @function_mapper { name="__zillians_api_space_getMaxKNN2DSizeX"; }
    function getMaxKNN2DSizeX():uint32;

    @function_mapper { name="__zillians_api_space_getMaxKNN2DSizeY"; }
    function getMaxKNN2DSizeY():uint32;

    @function_mapper { name="__zillians_api_space_getMaxKNN2D"; }
    function getMaxKNN2D():uint32;
}
